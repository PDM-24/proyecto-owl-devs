package com.owldevs.taskme.data.api

import com.google.gson.annotations.SerializedName
import com.owldevs.taskme.constants.Constants

data class ApiResponseSuccessful(
    //ola
    @SerializedName(value = Constants.RESPONSE_SUCCESSFUL)
    val result: String

)

data class ApiResponseError(
    @SerializedName(value = Constants.RESPONSE_ERROR)
    val message : String

)
