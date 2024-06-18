const express = require('express');
const httpError = require('http-errors');

const {
    User,
    Review,
    Category,
    Task,
    Notification
} = require('../models/TaskMeModels');
const { default: mongoose } = require('mongoose');


/*METODOS POST: */

//Registrar usuario:
const registerUser = async (req, res) => {
    try {

        const {
            nombreCompleto,
            correoElectronico,
            contrasenia,
            ubicacion,
            usuarioTasker,
            tarjetasAsociadas,
            perfilTasker,
        } = req.body;

        const existingUser = await User.findOne({ correo_electronico: correoElectronico });

        if (existingUser) {
            throw httpError(400, "Correo Electronico ya registrado. Utilice otro correo o inicie sesion");
        }

        const data = new User({
            nombre_completo: nombreCompleto,
            correo_electronico: correoElectronico,
            contrasenia: contrasenia,
            ubicacion: ubicacion,
            usuario_tasker: usuarioTasker,
            tarjetas_asociadas: tarjetasAsociadas,
            perfil_tasker: usuarioTasker ? perfilTasker : undefined,
        });

        await data.save()
        res.status(201).json({ "result": "ok" })
    } catch (error) {
        if (error.name === 'ValidationError') {
            const errors = Object.keys(error.errors).map(key => error.errors[key].message);
            return res.status(400).json({ "message": errors });
        }
        res.status(500).json({ "message": error.message })
    }
};

//Subir una nueva tarea:
const postTask = async (req, res) => {
    try {

        const {
            categoria,
            clienteId,
            taskerId,
            fecha,
            hora,
            ubicacion,
            precio,
            estado,
            metodoDePago
        } = req.body;

        const data = new Task({
            categoria: categoria,
            cliente_id: clienteId,
            tasker_id: taskerId,
            fecha: fecha,
            hora: hora,
            ubicacion: ubicacion,
            precio: precio,
            estado: estado,
            metodo_pago: metodoDePago,
        });

        await data.save();
        res.status(201).json({ "result": "ok" })
    } catch (error) {
        if (error.name === 'ValidationError') {
            const errors = Object.keys(error.errors).map(key => error.errors[key].message);
            return res.status(400).json({ "message": errors });
        }
        res.status(500).json({ "message": error.message })
    }
};

//Subir una reseña
const postReview = async (req, res) => {
    try {

        const {
            taskerId,
            autorId,
            texto,
            calificacion,
        } = req.body;

        const data = new Review({
            tasker_id: taskerId,
            autor_id: autorId,
            texto: texto,
            calificacion: calificacion,
        });

        await data.save();
        res.status(201).json({ "result": "ok" })
    } catch (error) {
        if (error.name === 'ValidationError') {
            const errors = Object.keys(error.errors).map(key => error.errors[key].message);
            return res.status(400).json({ "message": errors });
        }
        res.status(500).json({ "message": error.message })
    }
};

//Subir categoria nueva
const postCategory = async (req, res) => {
    try {

        const {
            nombre,
            icono
        } = req.body;

        const data = new Category({
            nombre: nombre,
            icono: icono
        });

        await data.save();
        res.status(201).json({ "result": "ok" });
    } catch (error) {
        if (error.name === 'ValidationError') {
            const errors = Object.keys(error.errors).map(key => error.errors[key].message);
            return res.status(400).json({ "message": errors });
        }
        res.status(500).json({ "message": error.message })
    }
};

//Guardar una notificacion
const postNotification = async (req, res) => {
    try {

        const {
            usuarioId,
            tipo,
            mensaje,
            estado
        } = req.body;

        const data = new Notification({
            id_usuario: usuarioId,
            tipo: tipo,
            mensaje: mensaje,
            estado: estado,
        });

        await data.save();
        res.status(201).json({ "result": "ok" });
    } catch (error) {
        if (error.name === 'ValidationError') {
            const errors = Object.keys(error.errors).map(key => error.errors[key].message);
            return res.status(400).json({ "message": errors });
        }
        res.status(500).json({ "message": error.message })
    }
};

/*METODOS GET*/

//Obtener usuario segun suscredenciales
const getUser = async (req, res) => {
    try {

        const {
            correoElectronico,
            contrasenia,
        } = req.body;

        const user = await User.findOne({ correo_electronico: correoElectronico });

        if (!user) {
            return res.status(404).json({ "message": "Usuario no encontrado" })
        };

        const passwordMatch = await user.comparePassword(contrasenia);

        if (!passwordMatch) {
            return res.status(401).json({ "message": "Credenciales incorrectas" });
        };

        res.status(200).json({
            id: user.id,
            correoElectronico: user.correo_electronico,
            nombre: user.nombre_completo,
            ubicacion: user.ubicacion,
            usuarioTasker: user.usuario_tasker,
            tarjetasAsociadas: user.tarjetas_asociadas,
            perfilTasker: user.perfil_tasker
        });
    } catch (error) {
        res.status(500).json({ "message": error.message });
    }
};

//Obtener todos los usuarios de una categoria
const getAllUsersByCatgory = async (req, res) => {
    try {

        const { categoryId } = req.params;

        if (!mongoose.Types.ObjectId.isValid(categoryId)) {
            return res.status(400).json({ "message": "Categoria invalida" });
        }

        const category = await Category.findById(categoryId)

        const users = await User.find({
            "perfil_tasker.habilidades.id_categoria": categoryId
        });

        res.status(200).json({
            "category": category.nombre,
            "users": users
        });

    } catch (error) {
        res.status(500).json({ "message": error.message });
    }
};


//Obtener todas las tareas de un usuario segun su rol en la app
const getAllTaskByRole = async (req, res) => {
    try {
        const { usuarioId, currentRole } = req.query;

        if (!mongoose.Types.ObjectId.isValid(usuarioId)) {
            return res.status(404).json({ "message": "Usuario invalido" });
        };

        let tasks;

        if (currentRole === 'cliente') {
            tasks = await Task.find({ cliente_id: usuarioId }).populate({
                path: 'categoria',
                select: { nombre: 1 }
            }).populate({
                path: 'tasker_id',
                select: {
                    nombre_completo: 1,
                    correo_electronico: 1,
                    ubicacion: 1,
                }
            });
        } else if (currentRole === 'tasker') {
            tasks = await Task.find({ tasker_id: usuarioId }).populate({
                path: 'categoria',
                select: { nombre: 1 }
            }).populate({
                path: 'cliente_id',
                select: {
                    nombre_completo: 1,
                    correo_electronico: 1,
                    ubicacion: 1,
                }
            });
        } else {
            return res.status(400).json({ "message": "Rol invalido" });
        };

        res.status(200).json(tasks);
    } catch (error) {
        res.status(500).json({ "message": error.message });
    }
};

//Obtener toda la informacion de 1 sola tarea
const getTaskById = async (req, res) => {
    try {
        const { taskId } = req.params;

        if (!mongoose.Types.ObjectId.isValid(taskId)) {
            return res.status(400).json({ "message": "Tarea invalida" });
        };

        const task = await Task.findById(taskId).populate({
            path: 'categoria',
            select: { nombre: 1 }
        }).populate({
            path: 'cliente_id',
            select: {
                nombre_completo: 1,
                correo_electronico: 1,
                ubicacion: 1
            }
        }).populate({
            path: 'tasker_id',
            select: {
                nombre_completo: 1,
                correo_electronico: 1,
                ubicacion: 1
            }
        });

        if (!task) return res.status(404).json({ "message": "Tarea no encontrada" });

        res.status(200).json(task);
    } catch (error) {
        res.status(500).json({ "message": error.message });
    }
};

//Obtener toda las notificaciones de un usuario
const getAllNotificationsByUser = async (req, res) => {
    try {
        const { usuarioId } = req.params;

        if (!mongoose.Types.ObjectId.isValid(usuarioId)) {
            return res.status(400).json({ "message": "Usuario invalido" });
        };

        const notifications = await Notification.find({ id_usuario: usuarioId });

        res.status(200).json(notifications);
    } catch (error) {
        res.status(500).json({ "message": error.message });
    }
};

//Obtener todas las categorias
const getAllCategories = async (req, res) => {
    try {
        const categories = await Category.find();

        if (!categories || Object.keys(categories).length === 0) {
            return res.status(404).json({ "message": "Categorias no encontradas" });
        };

        res.status(200).json(categories);
    } catch (error) {
        res.status(500).json({ "message": error.message });
    }
};


/*METODOS PUT/PATCH*/

// Actualizar cualquier dato ed un usuario
const updateUser = async (req, res) => {
    try {
        const { usuarioId } = req.params;
        const updateData = req.body;

        if (!mongoose.Types.ObjectId.isValid(usuarioId)) {
            return res.status(400).json({ "message": "Usuario invalido" });
        };

        const updatedUser = await User.findByIdAndUpdate(
            usuarioId,
            { $set: updateData },
            { new: true, runValidators: true }
        );

        if (!updatedUser) {
            return res.status(404).json({ message: "Usuario no encontrado" });
        }

        res.status(200).json({
            "message": "Usuario actualizado",
            updatedUser: updatedUser
        });
    } catch (error) {
        res.status(500).json({ "message": error.message });
    }
};

// Actualizar el estado de la tarea
const updateTaskState = async (req, res) => {
    try {
        const { taskId } = req.params;
        const { estado } = req.body;

        if (!mongoose.Types.ObjectId.isValid(taskId)) {
            return res.status(400).json({ message: "Tarea inválida" });
        };

        const validStatuses = ['Pendiente', 'En Progreso', 'Completada', 'Cancelada'];
        if (!validStatuses.includes(estado)) {
            return res.status(400).json({ message: "Estado inválido" });
        };

        const updatedTask = await Task.findByIdAndUpdate(
            taskId,
            { $set: { estado } },
            { new: true, runValidators: true }
        );

        if (!updatedTask) {
            return res.status(404).json({ message: "Tarea no encontrada" });
        };

        res.status(200).json({
            message: "Estado de la tarea actualizado",
            updatedTask
        });
    } catch (error) {
        res.status(500).json({ "message": error.message });
    }
};

// Actualizar el estado de la notificacion
const updateNotificationState = async (req, res) => {
    try {
        const { notificationId } = req.params;
        const { estado } = req.body;

        if (!mongoose.Types.ObjectId.isValid(notificationId)) {
            return res.status(400).json({ message: "Notificación inválida" });
        };

        const validStatuses = ['No Leída', 'Leída'];
        if (!validStatuses.includes(estado)) {
            return res.status(400).json({ message: "Estado inválido" });
        };

        const updatedNotification = await Notification.findByIdAndUpdate(
            notificationId,
            { $set: { estado } },
            { new: true, runValidators: true }
        );

        if (!updatedNotification) {
            return res.status(404).json({ message: "Notificación no encontrada" });
        };

        res.status(200).json({
            message: "Estado de la notificación actualizado",
            updatedNotification
        });
    } catch (error) {
        res.status(500).json({ "message": error.message });
    }
};


/*METODOS DELETE*/

//Eliminar notificacion
const deleteNotification = async (req, res) => {
    try {
        const { notificationId } = req.params;

        if (!mongoose.Types.ObjectId.isValid(notificationId)) {
            return res.status(400).json({ message: "Notificación inválida" });
        };

        const deletedNotification = await Notification.findByIdAndDelete(notificationId);

        if (!deletedNotification) {
            return res.status(404).json({ message: "Notificación no encontrada" });
        };

        res.status(200).json({ message: "Notificación eliminada" });
    } catch (error) {
        res.status(500).json({ "message": error.message });
    }
};


//Obtener todos los usuarois (para prueba)
const getAllUsers = async (req, res) => {
    try {
        const users = await User.find();

        if (!users || Object.keys(users).length === 0) {
            return res.status(404).json({ "message": "Usuarios no encontrados" });
        };

        res.status(200).json(users);
    } catch (error) {
        res.status(500).json({ "message": error.message });
    }
};

module.exports = {
    registerUser,
    postTask,
    postReview,
    postCategory,
    postNotification,
    getUser,
    getAllUsersByCatgory,
    getAllTaskByRole,
    getTaskById,
    getAllNotificationsByUser,
    getAllCategories,
    updateUser,
    updateTaskState,
    updateNotificationState,
    deleteNotification,
    getAllUsers
};