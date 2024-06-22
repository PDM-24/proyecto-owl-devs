package com.owldevs.taskme.model

import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.data.api.Habilidad
import com.owldevs.taskme.data.api.tarjetas_asociadas

data class UpdateUserRequest(
    val id: String?,
    val correo_electronico: String?,
    val nombre_completo: String?,
    val fotoPerfil: String?,
    val habilidades: List<Habilidad>?
)
