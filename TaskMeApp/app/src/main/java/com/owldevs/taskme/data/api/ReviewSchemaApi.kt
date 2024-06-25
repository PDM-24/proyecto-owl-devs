package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.constants.Constants
import java.io.Serial
import java.sql.Date

//Post
data class ReviewSchemaApi(
    @SerializedName(value = Constants.TASKER_ID)
    val taskerId: String = "",

    @SerializedName(value = Constants.AUTOR_ID)
    val autorId: String = "",

    @SerializedName(value = Constants.TEXTO)
    val texto: String = "",

    @SerializedName(value = Constants.CALIFICACION)
    val calificacion: Number = 0,

    @SerializedName(value = Constants.FECHA)
    val fecha: Date
)

data class ApiReviewsResponse(
    @SerializedName(value = "reviews")
    val reviews: List<ReviewResponseApi>
)

data class ReviewResponseApi(
    @SerializedName(value = "tasker_id")
    val taskerId: String,

    @SerializedName(value = "autor_id")
    val autorId: ReviewAuthor,

    @SerializedName(value = "texto")
    val texto: String,

    @SerializedName(value = "calificacion")
    val calificacion: Number,

    @SerializedName(value = "fecha")
    val fecha: Date
)

data class ReviewAuthor(
    @SerializedName(value = "_id")
    val id: String,
    @SerializedName(value = "nombre_completo")
    val nombre: String
)