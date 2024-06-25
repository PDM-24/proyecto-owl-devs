package com.owldevs.taskme.model

import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.data.api.tarjetas_asociadas

data class UserApiModel (
    val id: String,
    val correo_electronico: String,
    val nombre_completo: String,
    val fotoPerfil: String,
    val ubicacion: String,
    val usuarioTasker: Boolean,
    val tarjetasAsociadas: List<tarjetas_asociadas>,
    val perfilTasker: DetallesPerfilTasker?
)