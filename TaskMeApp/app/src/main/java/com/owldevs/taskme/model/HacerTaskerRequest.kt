package com.owldevs.taskme.model

import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.data.api.Habilidad

data class HacerTaskerRequest(
    val id: String?,
    val usuarioTasker: Boolean,
    val telefono: String?,
    val descripcion_personal: String?,
    val habilidades: List<Habilidad>?,

)
