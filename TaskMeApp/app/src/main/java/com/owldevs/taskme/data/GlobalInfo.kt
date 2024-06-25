package com.owldevs.taskme.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.owldevs.taskme.data.api.ApiTaskUserSuccessful
import com.owldevs.taskme.data.api.ApiUserByCategorySuccessful
import com.owldevs.taskme.data.api.ApiUserSuccessful
import com.owldevs.taskme.data.api.ApiUsersByCategoryResponse
import com.owldevs.taskme.data.api.NotificationApiResponse
import com.owldevs.taskme.data.api.ReviewResponseApi
import com.owldevs.taskme.data.api.TaskAApiIdResponse
import com.owldevs.taskme.data.api.TaskApiIdResponse

//Id's
var taskerId: String = ""
var currentUserId: String = ""
var currentUserRole: String = ""
var categoryId: String = ""
var taskId: String = ""
var notificationId: String = ""
var currentRole: Boolean = false
var categoria: String = ""
var taskernombre: String = ""
var precio: Number = 0
var estado: String = ""
var metodo_pago: String = ""
var ubicacion: String = ""




//Listas
var userReviewsList = mutableStateListOf<ReviewResponseApi>()
var usersCategoryList = mutableStateListOf<ApiUserByCategorySuccessful>()
var usersNotificationsList = mutableStateListOf<NotificationApiResponse>()
var userTaskList = mutableStateListOf<ApiTaskUserSuccessful>()



//Extras:
var currentCategory: String = ""
var currentTasker: ApiUserByCategorySuccessful = ApiUserByCategorySuccessful()