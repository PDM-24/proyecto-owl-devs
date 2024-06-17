package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.constants.Constants


data class CategoryApi(
    @SerializedName(value = Constants.NOMBRE)
    val nombre: String = "",

    @SerializedName(value =  Constants.ICONO)
    val icono: String = ""
)
