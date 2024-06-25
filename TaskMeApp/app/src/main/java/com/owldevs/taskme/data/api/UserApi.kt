package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.constants.Constants
import java.util.Date

data class UserApi(
    @SerializedName(value = Constants.NOMBRE_COMPLETO)
    val nombre_completo: String = "",

    @SerializedName(value = Constants.CORREO_ELECTRONICO)
    val correo_electronico: String = "",

    @SerializedName(value = Constants.CONTRASENIA)
    val contrasenia: String = "",

    @SerializedName(value = Constants.UBICACION)
    val ubicacion: String = "",

    @SerializedName(value = Constants.USUARIO_TASKER)
    val usuario_tasker: Boolean = false,

    @SerializedName(value = Constants.METODOS_PAGO)
    val metodos_pago: MutableList<tarjetas_asociadas> = arrayListOf(),

    @SerializedName(value = Constants.PERFIL_TASKER)
    val PerfilTasker: DetallesPerfilTasker? = null,

    )

data class tarjetas_asociadas(
    @SerializedName(value = Constants.NUMERO)
    val numero: String,

    @SerializedName(value = Constants.FECHA_VENCIMIENTO)
    val fecha_vencimiento: String,

    @SerializedName(value = Constants.NUMERO_CV)
    val numero_cv: String
)

data class DetallesPerfilTasker(
    @SerializedName(value = Constants.TELFONO)
    val telefono: String = "",

    @SerializedName(value = Constants.DESCRIPCION_PERSONAL)
    val descripcion_personal: String = "",

    @SerializedName(value = Constants.FECHA_UNION)
    val fecha_union: String = "",

    @SerializedName(value = Constants.TRABAJOS_REALIZADOS)
    val trabajos_realizados: Int = 0,

    @SerializedName(value = Constants.PROMEDIO_CALIFIACIONES)
    val promedio_calificaciones: Int = 0,

    @SerializedName(value = Constants.HABILIDADES)
    val habilidades: List<Habilidad> = listOf(),

    @SerializedName(value = Constants.GALERIA_TRABAJOS)
    val galeria_trabajos: List<GaleriaTrabajo> = listOf()
)

data class Habilidad(
    @SerializedName(value = Constants.ID_CATEGORIA)
    val id_categoria: String? = null,

    @SerializedName(value = Constants.NOMBRE)
    val nombre: String? = ""
)

data class GaleriaTrabajo(
    @SerializedName(value = Constants.URL)
    val url: String = "",

    @SerializedName(value = Constants.DESCRIPCION)
    val descripcion: String = "",

    @SerializedName(value = Constants.FECHA)
    val fecha: Date? = null
)


data class LoginRequest(
    @SerializedName(Constants.CORREO_ELECTRONICO)
    val correoElectronico: String,

    @SerializedName(Constants.CONTRASENIA)
    val contrasenia: String
)

