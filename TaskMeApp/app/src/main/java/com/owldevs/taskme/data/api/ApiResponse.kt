package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.constants.Constants
import com.owldevs.taskme.model.User
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
    val usuarios: List<ApiUserSuccessful>
)

data class ApiUserSuccessful(
    @SerializedName(value = Constants.ID_USUARIO)
    val id: String,

    @SerializedName("correoElectronico")
    val correoElectronico: String,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("fotoPerfil")
    val fotoPerfil: String,

    @SerializedName("ubicacion")
    val ubicacion: String,

    @SerializedName("usuarioTasker")
    val usuarioTasker: Boolean,

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

