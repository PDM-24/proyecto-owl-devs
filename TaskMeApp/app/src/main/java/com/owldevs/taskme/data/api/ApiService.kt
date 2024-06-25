package com.owldevs.taskme.data.api


import com.owldevs.taskme.model.HacerTaskerRequest
import com.owldevs.taskme.utils.Constants

import com.owldevs.taskme.model.UpdateUserRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //post user
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.POST_USER_PATH)
    suspend fun registerUser(@Body user: UserApi): ApiResponseSuccessful

    //post review
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.POST_REVIEW_PATH)
    suspend fun postReview(@Body review: ReviewSchemaApi): ApiResponseSuccessful


    //post task
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.POST_TASK_PATH)
    suspend fun postTask(@Body task: TaskApi): ApiResponseSuccessful


    //post notification
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.POST_NOTIFICATION_PATH)
    suspend fun postNotification(@Body notification: NotificationApi): ApiResponseSuccessful

    //post category
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.POST_CATEGORY_PATH)
    suspend fun postCategory(@Body category: CategoryApi): ApiResponseSuccessful

    // post chat preview
    @Headers("Content-Type: application/json")
    @POST(Constants.API_PATH + Constants.POST_CHAT_PREVIEW_PATH)
    suspend fun createChatPreview(@Body chatPreview: ChatPreviewApi): ApiChatPreviewResponse



    //get user
    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.API_PATH + Constants.GET_USER_LOGIN_PATH)
    suspend fun loginUser(@Body loginRequest: LoginRequest): ApiUserSuccessful

    //Gets

    // get chat previews by user
    @Headers("Content-Type: application/json")
    @GET(Constants.API_PATH + Constants.GET_CHAT_PREVIEWS_BY_USER_PATH)
    suspend fun getChatPreviewsByUser(@Path("usuarioId") usuarioId: String): ApiChatPreviewsResponse


    // get category
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + "/categories")
    suspend fun getCategories(): List<CategoryApi>

    //Get Reviews By User
    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.GET_ALL_REVIEWS_BY_USER_PATH)
    suspend fun getAllReviewsByUser (
        @Path("usuarioId") usuarioId: String
    ): ApiReviewsResponse

    @Headers(value = ["Content-Type: application/json"])
    @GET(value = Constants.API_PATH + Constants.GET_ALL_USERS_BY_CATEGORY_PATH)
    suspend fun getAllUsersByCategory (
        @Path("categoryId") categoryId: String
    ): ApiUsersByCategoryResponse

    @Headers("Content-Type: application/json")
    @GET(Constants.API_PATH + Constants.GET_TASK_BY_ID_PATH)
    suspend fun getTasksById(
        @Path("taskId") taskId: String
    ): TaskApiIdResponse

    @Headers("Content-Type: application/json")
    @GET(Constants.API_PATH + Constants.GET_ALL_NOTIFICATIONS_BY_USER_PATH)
    suspend fun getAllNotificationsByUser(
        @Path("usuarioId") usuarioId: String
    ): NotificationsApiResponseList


    //getTasks
    @Headers("Content-Type: application/json")
    @GET(Constants.API_PATH + "/tasks/role")
    suspend fun getTaskbyUser(
        @Query("usuarioId") usuarioId: String,
        @Query("currentRole") role: String,
    ): List<ApiTaskUserSuccessful>

    //PATCH

    //update user
    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = Constants.API_PATH + Constants.UPDATE_USER_PATH)
    suspend fun updateUser(
        @Path("usuarioId") usuarioId: String?,
        @Body updateUserRequest: UpdateUserRequest
    ): ApiUpdateSuccessful

    @Headers(value = ["Content-Type: application/json"])
    @PATCH(value = Constants.API_PATH + Constants.UPDATE_USER_PATH)
    suspend fun turnTasker(
        @Path("usuarioId") usuarioId: String?,
        @Body hacerTaskerRequest: HacerTaskerRequest
    ): ApiUpdateSuccessful


}