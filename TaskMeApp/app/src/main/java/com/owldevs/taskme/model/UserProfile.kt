package com.owldevs.taskme.model

data class UserProfile(
    val correoElectronico: String,
    val nombre: String,
    val ubicacion: String,
    val usuarioTasker: Boolean,
    val tarjetasAsociadas: List<String>,
    val perfilTasker: PerfilTasker
)

data class PerfilTasker(
    val fecha_union: String,
    val trabajos_realizados: Int,
    val promedio_calificaciones: Double,
    val habilidades: List<String>,
    val galeria_trabajos: List<String>
)

data class Tarjeta(
   val numero: Double
)
