package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.constants.Constants
import java.sql.Date

data class TaskApi(
    @SerializedName(value = Constants.CATEGORIA)
    val categoria: String,

    @SerializedName(value = Constants.CLIENTE_ID)
    val cliente_id: String,

    @SerializedName(value = Constants.TASKER_ID)
    val tasker_id: String,

    @SerializedName(value = Constants.FECHA)
    val fecha: Date,

    @SerializedName(value = Constants.HORA)
    val hora: String,

    @SerializedName(value = Constants.UBICACION)
    val ubicacion: String,

    @SerializedName(value = Constants.PRECIO)
    val precio: String,

    @SerializedName(value = Constants.ESTADO)
    val estado: String,

    @SerializedName(value = Constants.METODO_PAGO)
    val metodo_pago: String
)

data class TaskApiIdResponse(
    @SerializedName(value = "_id")
    val id: String,

    @SerializedName(value = Constants.CATEGORIA)
    val categoria: Categoria,

    @SerializedName(value = Constants.CLIENTE_ID)
    val cliente_id: User,

    @SerializedName(value = Constants.TASKER_ID)
    val tasker_id: User,

    @SerializedName(value = Constants.FECHA)
    val fecha: Date,

    @SerializedName(value = Constants.HORA)
    val hora: String,

    @SerializedName(value = Constants.UBICACION)
    val ubicacion: String,

    @SerializedName(value = Constants.PRECIO)
    val precio: String,

    @SerializedName(value = Constants.ESTADO)
    val estado: String,

    @SerializedName(value = Constants.METODO_PAGO)
    val metodo_pago: String
)

data class Categoria(
    @SerializedName(value = "_id")
    val id: String,
    @SerializedName(value = "nombre")
    val nombre: String
)



data class TaskCategoria(
    @SerializedName(value = "_id")
    val id: String,
    @SerializedName(value = "nombre")
    val nombre: String
)

data class Cliente_id(
    @SerializedName(value = "_id")
    val id: String,
    @SerializedName(value = "nombre_completo")
    val nombreCompleto: String,
    @SerializedName(value = "correo_electronico")
    val correoElectronico: String,
    @SerializedName(value = "ubicacion")
    val ubicacion: String
)



data class User(
    @SerializedName(value = "_id")
    val id: String,
    @SerializedName(value = "nombre_completo")
    val nombreCompleto: String,
    @SerializedName(value = "correo_electronico")
    val correoElectronico: String,
    @SerializedName(value = "ubicacion")
    val ubicacion: String
)
