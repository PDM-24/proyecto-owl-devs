package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.constants.Constants
import com.owldevs.taskme.model.User

data class ApiResponseSuccessful(
    //ola
    @SerializedName(value = Constants.RESPONSE_SUCCESSFUL)
    val result: String,


    @SerializedName("user")
    val user: User,

    @SerializedName(value = Constants.RESPONSE_ERROR)
    val message : String

)

data class ApiResponseError(
    @SerializedName(value = Constants.RESPONSE_ERROR)
    val message : String

)


