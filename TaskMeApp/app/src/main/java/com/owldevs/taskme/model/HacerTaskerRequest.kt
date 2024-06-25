package com.owldevs.taskme.model

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.data.api.DetallesPerfilTasker
//import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.data.api.Habilidad
import com.owldevs.taskme.utils.Constants

/*data class HacerTaskerRequest(
    val id: String?,
    val usuarioTasker: Boolean,
    val telefono: String?,
    val descripcion_personal: String?,
    val habilidades: List<Habilidad>?,

)*/


data class HacerTaskerRequest(
    @SerializedName(value = Constants.USUARIO_TASKER)
    val usuarioTasker: Boolean,

    @SerializedName(value = "perfil_tasker")
    val perfil_tasker: DetallesPerfilTasker?,
)
