package com.owldevs.taskme.data.api

import com.owldevs.taskme.constants.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    //post user
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.POST_USER_PATH)
    suspend fun registerUser( @Body user : UserApi): ApiResponseSuccessful

    //post review
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.POST_REVIEW_PATH)
    suspend fun postReview( @Body review : ReviewSchemaApi): ApiResponseSuccessful


    //post task
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.POST_TASK_PATH)
    suspend fun postTask( @Body task : TaskApi): ApiResponseSuccessful


    //post notification
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.POST_NOTIFICATION_PATH)
    suspend fun postNotification( @Body notification : NotificationApi): ApiResponseSuccessful

    //post category
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.POST_CATEGORY_PATH)
    suspend fun postCategory( @Body category : CategoryApi): ApiResponseSuccessful

    //get user
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.GET_USER_LOGIN_PATH)
    suspend fun loginUser(@Body loginRequest: LoginRequest): ApiUserSuccessful

    // get category
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + "/categories")
    suspend fun getCategories(): List<CategoryApi>


}