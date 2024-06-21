const { mongoose, Schema } = require('mongoose');
const bcrypt = require('bcrypt');

const userSchema = new mongoose.Schema({
    nombre_completo: {
        type: String,
        required: [true, 'El nombre completo es obligatorio'],
        minlength: [3, 'El nombre completo debe tener al menos 3 caracteres']
    },
    correo_electronico: {
        type: String,
        required: [true, 'El correo electrónico es obligatorio'],
        unique: true,
        match: [/.+\@.+\..+/, 'Por favor ingresa un correo electrónico válido']
    },
    contrasenia: {
        type: String,
        required: [true, 'La contraseña es obligatoria'],
        minlength: [6, 'La contraseña debe tener al menos 6 caracteres']
    },
    foto_perfil: {
        type: String,
        required: false
    },
    ubicacion: {
        type: String,
        required: [true, 'La ubicación es obligatoria']
    },
    usuario_tasker: {
        type: Boolean,
        default: false
    },
    tarjetas_asociadas: [
        {
            numero: {
                type: String,
                required: false,
                match: [/^\d{16}$/, 'El número de tarjeta debe tener 16 dígitos']
            },
            fecha_vencimiento: {
                type: String,
                require: false,
                match: [/^(0[1-9]|1[0-2])\/\d{2}$/, 'La fecha de vencimiento debe tener el formato MM/AA']
            },
            numero_cv: {
                type: String,
                require: false,
                match: [/^\d{3}$/, 'El CVV debe tener 3 dígitos']
            }
        }
    ],
    perfil_tasker: {
        telefono: {
            type: String,
            required: false,
            match: [/^\d{10}$/, 'El teléfono debe tener 10 dígitos']
        },
        descripcion_personal: {
            type: String,
            required: false,
            maxlength: [500, 'La descripción personal no debe exceder los 500 caracteres']
        },
        fecha_union: {
            type: Date,
            default: Date.now()
        },
        trabajos_realizados: {
            type: Number,
            default: 0
        },
        promedio_calificaciones: {
            type: Number,
            default: 0
        },
        habilidades: [
            {
                id_categoria: {
                    type: Schema.Types.ObjectId,
                    require: false,
                    ref: 'Category'
                },
                nombre: {
                    type: String,
                    required: false
                }
            }
        ],
        galeria_trabajos: [
            {
                url: {
                    type: String,
                    required: false
                },
                descripcion: {
                    type: String,
                    required: false
                },
                fecha: {
                    type: Date,
                    default: Date.now()
                }
            }
        ]
    }
}, { _id: true });

userSchema.pre('save', function (next) {
    if (!this.usuario_tasker) {
        this.perfil_tasker = undefined;
    }
    next();
});

userSchema.pre('save', async function (next) {
    if (this.isModified('contrasenia') || this.isNew) {
        const salt = await bcrypt.genSalt(10);
        this.contrasenia = await bcrypt.hash(this.contrasenia, salt);
    }
    next();
});

userSchema.methods.comparePassword = async function (password) {
    return bcrypt.compare(password, this.contrasenia);
};


const reviewSchema = new mongoose.Schema({
    tasker_id: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: [true, 'El ID del tasker es obligatorio']
    },
    autor_id: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: [true, 'El ID del autor es obligatorio']
    },
    texto: {
        type: String,
        required: [true, 'El texto de la reseña es obligatorio'],
        minlength: [10, 'El texto de la reseña debe tener al menos 10 caracteres'],
        maxlength: [500, 'El texto de la reseña no debe exceder los 500 caracteres']
    },
    calificacion: {
        type: Number,
        required: [true, 'La calificación es obligatoria'],
        min: [1, 'La calificación mínima es 1'],
        max: [5, 'La calificación máxima es 5']
    },
    fecha: {
        type: Date,
        default: Date.now()
    }
}, { _id: true });

reviewSchema.post('save', async function (doc, next) {
    try {
        const tasker = await this.model('User').findById(doc.tasker_id);

        if (!tasker.perfil_tasker) {
            return next();
        };

        const reviews = await this.model('Review').find({ tasker_id: doc.tasker_id });
        const totalReviews = reviews.length;
        const averageRating = totalReviews > 0 ? reviews.reduce((sum, review) => sum + review.calificacion, 0) / totalReviews : 0;

        await this.model('User').findByIdAndUpdate(doc.tasker_id, {
            $set: { 'perfil_tasker.promedio_calificaciones': averageRating }
        });

        next();
    } catch (error) {
        next(error);
    }
});


const categorySchema = new mongoose.Schema({
    nombre: {
        type: String,
        required: [true, 'El nombre es obligatorio'],
        minlength: [3, 'El nombre debe tener al menos 3 caracteres'],
        maxlength: [50, 'El nombre no debe exceder los 50 caracteres']
    },
    icono: {
        type: String,
        required: [true, 'El icono es obligatorio'],
        match: [/^https?:\/\/.+\.(jpg|jpeg|png|svg)$/, 'El icono debe ser una URL válida a una imagen (jpg, jpeg, png, svg)']
    },
}, { _id: true });


const taskSchema = new mongoose.Schema({
    categoria: {
        type: Schema.Types.ObjectId,
        ref: 'Category',
        required: [true, 'La categoría es obligatoria']
    },
    cliente_id: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: [true, 'El ID del cliente es obligatorio']
    },
    tasker_id: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: [true, 'El ID del tasker es obligatorio']
    },
    fecha: {
        type: Date,
        required: [true, 'La fecha es obligatoria'],
        min: [Date.now(), 'La fecha no puede ser en el pasado']
    },
    hora: {
        type: String,
        required: [true, 'La hora es obligatoria'],
        match: [/^([01]\d|2[0-3]):([0-5]\d)$/, 'La hora debe tener el formato HH:mm']
    },
    ubicacion: {
        type: String,
        required: [true, 'La ubicación es obligatoria'],
        maxlength: [100, 'La ubicación no debe exceder los 100 caracteres']
    },
    precio: {
        type: Number,
        required: [true, 'El precio es obligatorio'],
        min: [0, 'El precio no puede ser negativo']
    },
    estado: {
        type: String,
        required: [true, 'El estado es obligatorio'],
        enum: ['Pendiente', 'En Progreso', 'Completada', 'Cancelada']
    },
    metodo_pago: {
        type: String,
        required: [true, 'El método de pago es obligatorio'],
        enum: ['Efectivo', 'Tarjeta']
    }
}, { _id: true });


taskSchema.post('save', async function (doc, next) {
    try {
        const tasker = await this.model('User').findById(doc.tasker_id);

        if (!tasker.perfil_tasker) {
            return next();
        }

        const completedTasks = await this.model('Task').countDocuments({ tasker_id: doc.tasker_id, estado: 'Completada' });

        await this.model('User').findByIdAndUpdate(doc.tasker_id, {
            $set: { 'perfil_tasker.trabajos_realizados': completedTasks }
        });

        next();
    } catch (error) {
        next(error);
    }
});


const notificationSchema = new mongoose.Schema({
    id_usuario: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: [true, 'El ID del usuario es obligatorio']
    },
    tipo: {
        type: String,
        required: [true, 'El tipo de notificación es obligatorio']
    },
    mensaje: {
        type: String,
        required: [true, 'El mensaje es obligatorio'],
        minlength: [10, 'El mensaje debe tener al menos 10 caracteres'],
        maxlength: [500, 'El mensaje no debe exceder los 500 caracteres']
    },
    estado: {
        type: String,
        required: [true, 'El estado es obligatorio'],
        enum: ['No Leída', 'Leída']
    },
    fecha_creacion: {
        type: Date,
        default: Date.now()
    }
}, { _id: true });


const User = mongoose.model('User', userSchema);
const Review = mongoose.model('Review', reviewSchema);
const Category = mongoose.model('Category', categorySchema);
const Task = mongoose.model('Task', taskSchema);
const Notification = mongoose.model('Notification', notificationSchema);

module.exports = {
    User,
    Review,
    Category,
    Task,
    Notification
};