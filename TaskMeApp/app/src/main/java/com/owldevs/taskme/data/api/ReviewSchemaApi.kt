package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.constants.Constants
import java.sql.Date

data class ReviewSchemaApi(
    @SerializedName(value = Constants.TASKER_ID)
    val taskerId: String = "",

    @SerializedName(value = Constants.AUTOR_ID)
    val autorId: String= "",

    @SerializedName(value = Constants.TEXTO)
    val texto: String = "",

    @SerializedName(value = Constants.CALIFICACION)
    val calificacion: Number = 0,

    @SerializedName(value = Constants.FECHA)
    val fecha: Date
)