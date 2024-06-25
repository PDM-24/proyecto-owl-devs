package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.utils.Constants
import java.util.Date

data class ApiResponseSuccessful(
    //ola
    @SerializedName(value = Constants.RESPONSE_SUCCESSFUL)
    val result: String,
    )

data class ApiUsersByCategoryResponse(
    @SerializedName(value = "category")
    val categoria: String,
    @SerializedName(value = "users")
    val usuarios: List<ApiUserByCategorySuccessful>
)

data class ApiUserSuccessful(
    @SerializedName(value = Constants.ID_USUARIO)
    val id: String = "",

    @SerializedName("correoElectronico")
    val correoElectronico: String = "",

    @SerializedName("nombre")
    val nombre: String = "",

    @SerializedName("fotoPerfil")
    val fotoPerfil: String = "",

    @SerializedName("ubicacion")
    val ubicacion: String = "",

    @SerializedName("usuarioTasker")
    val usuarioTasker: Boolean = false,

    @SerializedName("tarjetasAsociadas")
    val tarjetasAsociadas: List<tarjetas_asociadas> = arrayListOf(),

    @SerializedName("perfilTasker")
    val perfilTasker: DetallesPerfilTasker? = null
)

data class ApiResponseError(
    @SerializedName(value = Constants.RESPONSE_ERROR)
    val message: String

)


data class ApiUpdateSuccessful(
    @SerializedName(value = "message")
    val result: String,
    @SerializedName(value = "updatedUser")
    val usuarioUpdated: ApiUserUpdatedSuccessful
)



data class ApiUserUpdatedSuccessful(
    @SerializedName(value = "_id")
    val id: String,

    @SerializedName(value = "correo_electronico")
    val correoElectronico: String,

    @SerializedName(value = "nombre_completo")
    val nombre: String,

    @SerializedName(value = "foto_perfil")
    val fotoPerfil: String,

    @SerializedName(value = "ubicacion")
    val ubicacion: String,

    @SerializedName(value = "usuario_tasker")
    val usuarioTasker: Boolean,

    @SerializedName(value = "tarjetas_asociadas")
    val tarjetasAsociadas: List<tarjetas_asociadas> = arrayListOf(),

    @SerializedName(value = "perfil_tasker")
    val perfilTasker: DetallesPerfilTasker? = null
)


data class ApiTaskUserSuccessful(
    @SerializedName(value = "_id")
    val id: String,
    @SerializedName(value = "categoria")
    val categoria: TaskCategoria,
    @SerializedName(value = "cliente_id")
    val cliente_id: Cliente_id,
    @SerializedName(value = "tasker_id")
    val tasker_id: String,
    @SerializedName(value = "hora")
    val fecha: String,
    @SerializedName(value = "fecha")
    val hora: String,
    @SerializedName(value = "ubicacion")
    val ubicacion: String,
    @SerializedName(value = "precio")
    val precio: Number,
    @SerializedName(value = "estado")
    val estado: String,
    @SerializedName(value = "metodo_pago")
    val metodo_pago: String
)

data class ApiUserByCategorySuccessful(
    @SerializedName(value = "_id")
    val id: String = "",

    @SerializedName("correo_electronico")
    val correoElectronico: String = "",

    @SerializedName("nombre_completo")
    val nombre: String = "",

    @SerializedName("foto")
    val fotoPerfil: String = "",

    @SerializedName("ubicacion")
    val ubicacion: String = "",

    @SerializedName("usuario_tasker")
    val usuarioTasker: Boolean = false,

    @SerializedName("tarjetas_asociadas")
    val tarjetasAsociadas: List<tarjetas_asociadas> = arrayListOf(),

    @SerializedName("perfil_tasker")
    val perfilTasker: DetallesPerfilTaskerCategory = DetallesPerfilTaskerCategory()
)

data class DetallesPerfilTaskerCategory(
    @SerializedName(value = "telefono")
    val telefono: String = "",

    @SerializedName(value = "descripcion_personal")
    val descripcionPersonal: String = "",

    @SerializedName(value = "fecha_union")
    val fechaUnion: Date = Date(),

    @SerializedName(value = "trabajos_realizados")
    val trabajosRealizados: Int = 0,

    @SerializedName(value = "promedio_calificaciones")
    val promedioCalificaciones: Double = 0.0,

    @SerializedName(value = "foto")
    val foto: String = "",

    @SerializedName(value = "habilidades")
    val habilidades: List<Habilidad> = listOf(),

    @SerializedName(value = "galeria_trabajos")
    val galeriaTrabajos: List<GaleriaTrabajo> = listOf()

)
data class TurnTaskerSuccessful(
    @SerializedName(value = Constants.ID_USUARIO)
    val id: String,

    @SerializedName(value=Constants.TELFONO)
    val telefono: String,

    @SerializedName(value=Constants.USUARIO_TASKER)
    val usuarioTasker: Boolean,

    @SerializedName(value=Constants.DESCRIPCION_PERSONAL)
    val descripcion_personal: String,


    @SerializedName("habilidades")
    val habilidades: List<Habilidad>
)