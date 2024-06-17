package com.owldevs.taskme.data.api


import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.constants.Constants
import java.sql.Date

data class NotificationApi(
    @SerializedName(value = Constants.ID_USUARIO)
    val id_usuario : String = "",

    @SerializedName(value = Constants.TIPO)
    val tipo: String = "",

    @SerializedName(value = Constants.MENSAJE)
    val mensaje: String = "",

    @SerializedName(value = Constants.ESTADO)
    val estado: String = "",

    @SerializedName(value = Constants.FECHA_CREACION)
    val fecha_creacion: Date
)