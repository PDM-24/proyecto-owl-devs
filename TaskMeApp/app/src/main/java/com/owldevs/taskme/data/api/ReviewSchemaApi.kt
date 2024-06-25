package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.utils.Constants
import java.util.Date

//Post
data class ReviewSchemaApi(
    @SerializedName(value = "taskerId")
    val taskerId: String = "",

    @SerializedName(value = "autorId")
    val autorId: String = "",

    @SerializedName(value = "texto")
    val texto: String = "",

    @SerializedName(value = "calificacion")
    val calificacion: Number = 0
)

data class ApiReviewsResponse(
    @SerializedName(value = "result")
    val result: String = "",

    @SerializedName(value = "reviews")
    val reviews: List<ReviewResponseApi> = listOf()
)

data class ReviewResponseApi(

    @SerializedName(value = "_id")
    val id: String = "",

    @SerializedName(value = "tasker_id")
    val taskerId: String = "",

    @SerializedName(value = "autor_id")
    val autorId: ReviewAuthor = ReviewAuthor(),

    @SerializedName(value = "texto")
    val texto: String = "",

    @SerializedName(value = "calificacion")
    val calificacion: Number = 0,

    @SerializedName(value = "fecha")
    val fecha: Date = Date()
)

data class ReviewAuthor(
    @SerializedName(value = "_id")
    val id: String = "",
    @SerializedName(value = "nombre_completo")
    val nombre: String = ""
)