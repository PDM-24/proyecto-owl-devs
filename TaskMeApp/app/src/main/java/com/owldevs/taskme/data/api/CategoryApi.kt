package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.constants.Constants
import com.owldevs.taskme.utils.Constants

data class CategoryApi(
    @SerializedName(value = Constants.NOMBRE)
    val nombre: String = "",

    @SerializedName(value =  Constants.ICONO)
    val icono: String = ""
)
