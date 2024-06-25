package com.owldevs.taskme.model

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.data.api.DetallesPerfilTasker
//import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.data.api.Habilidad
import com.owldevs.taskme.data.api.tarjetas_asociadas

data class UpdateUserRequest(
    @SerializedName(value = "correo_electronico")
    val correo_electronico: String?,
    @SerializedName(value = "nombre_completo")
    val nombre_completo: String?,
    @SerializedName(value = "foto_perfil")
    val foto_perfil: String?,
    @SerializedName(value = "perfil_tasker")
    val perfil_tasker: DetallesPerfilTasker?,
)
