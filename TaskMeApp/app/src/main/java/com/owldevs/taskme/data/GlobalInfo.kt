package com.owldevs.taskme.data

import androidx.compose.runtime.mutableStateListOf
import com.owldevs.taskme.data.api.ApiUserSuccessful
import com.owldevs.taskme.data.api.NotificationApiResponse
import com.owldevs.taskme.data.api.ReviewResponseApi
import com.owldevs.taskme.data.api.TaskApiIdResponse

//Id's
var taskerId: String = ""
var currentUserId: String = ""
var currentUserRole: String = ""
var categoryId: String = ""
var taskId: String = ""
var notificationId: String = ""

//Listas
var userReviewsList = mutableStateListOf<ReviewResponseApi>()
var usersCategoryList = mutableStateListOf<ApiUserSuccessful>()
var usersNotificationsList = mutableStateListOf<NotificationApiResponse>()

//Extras:
var currentCategory: String = ""