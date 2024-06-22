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


data class ApiUserSuccessful(

    @SerializedName("correoElectronico")
    val correoElectronico: String,

    @SerializedName("nombre")
    val nombre: String,

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
    val message : String

)

