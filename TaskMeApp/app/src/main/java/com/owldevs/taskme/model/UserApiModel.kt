package com.owldevs.taskme.model

import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.data.api.GaleriaTrabajo

import com.owldevs.taskme.data.api.Habilidad
import com.owldevs.taskme.data.api.tarjetas_asociadas

data class UserApiModel(
    val id: String,
    val correo_electronico: String,
    val nombre_completo: String,
    val fotoPerfil: String,
    val ubicacion: String,
    val usuarioTasker: Boolean,
    val tarjetasAsociadas: List<tarjetas_asociadas>,
    val perfilTasker: DetallesPerfilTasker?
)

data class DetallesPerfilTaskerModel(
    val telefono: String,

    val descripcion_personal: String,

    val fecha_union: String?,

    val trabajos_realizados: Int?,

    val promedio_calificaciones: Int?,

    val habilidades: List<Habilidad>?,

    val galeria_trabajos: List<GaleriaTrabajo>?

    )


