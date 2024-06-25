package com.owldevs.taskme.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owldevs.taskme.data.api.ApiChatPreviewsResponse
import com.owldevs.taskme.data.api.ApiClient
import com.owldevs.taskme.data.api.ApiResponseSuccessful
import com.owldevs.taskme.data.api.ApiUserByCategorySuccessful
import com.owldevs.taskme.data.api.ApiUserSuccessful
import com.owldevs.taskme.data.api.ApiUserUpdatedSuccessful
import com.owldevs.taskme.data.api.ChatPreviewApi
import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.data.api.DetallesPerfilTaskerCategory
import com.owldevs.taskme.model.UserApiModel
import com.owldevs.taskme.data.api.ReviewSchemaApi
import com.owldevs.taskme.data.categoryId
import com.owldevs.taskme.data.currentCategory
import com.owldevs.taskme.data.currentTasker
import com.owldevs.taskme.data.currentUserId
import com.owldevs.taskme.data.taskId
import com.owldevs.taskme.data.taskerId
import com.owldevs.taskme.data.userReviewsList
import com.owldevs.taskme.data.usersCategoryList
import com.owldevs.taskme.data.usersNotificationsList
import com.owldevs.taskme.model.UpdateUserRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException


class UserApiViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    var uiState: StateFlow<UiState> = _uiState

    private val _currentUser = MutableLiveData<UserApiModel?>()
    var currentUser: LiveData<UserApiModel?> = _currentUser

    private val _profileUpdated = MutableLiveData<Boolean>()
    val profileUpdated: LiveData<Boolean> get() = _profileUpdated

    var errorMessage by mutableStateOf("")
        private set

    private val _mailbox = MutableLiveData<List<ApiUserByCategorySuccessful>>()
    val mailbox: LiveData<List<ApiUserByCategorySuccessful>> = _mailbox

    init {
        _mailbox.value = mutableListOf()
    }


    fun setCurrentUser(userProfile: UserApiModel?) {
        _currentUser.value = userProfile
    }

    fun changeUserRole(role: String) {
        _currentUser.value = _currentUser.value?.copy(usuarioTasker = role == "tasker")
        Log.i("UserApiViewModel", "User role updated: ${_currentUser.value}")
    }

    fun updateProfile(updatedProfile: UpdateUserRequest) {
        viewModelScope.launch {
            try {
                val response =
                    ApiClient.apiService.updateUser(_currentUser.value?.id, updatedProfile)
                Log.i("Updated Profile", "Login response: ${response.result}")
                Log.i("Updated Profile", "Updated User: ${response.usuarioUpdated}")
                _currentUser.value = response.usuarioUpdated.toUserApiModel()
                _profileUpdated.value = true
            } catch (e: Exception) {
                errorMessage = "Error al actualizar el perfil: ${e.message}"
                Log.e("UpdateProfile", "Error al actualizar el perfil", e)
            }
        }
    }

    fun getAllReviewsById(){
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response = ApiClient.apiService.getAllReviewsByUser(taskerId)

                val taskerReview = response.reviews

                userReviewsList.clear()
                userReviewsList.addAll(taskerReview)

                // Calcular el promedio de calificaciones
                val promedio = if (taskerReview.isNotEmpty()) {
                    taskerReview.map { it.calificacion.toDouble() }.average()
                } else {
                    0.0
                }
                currentTasker.perfilTasker.promedioCalificaciones = promedio

            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("MainViewModel (PostReview) http", e.message())
                        _uiState.value = UiState.Error(e.message())
                    }

                    else -> {
                        Log.i("MainViewModel (PostReview) else", e.toString())
                        _uiState.value = UiState.Error(e.toString())
                    }
                }
            }
        }
    }

    fun postReviewTasker(review: ReviewSchemaApi) {
        viewModelScope.launch {

            try {
                _uiState.value = UiState.Loading

                val response = ApiClient.apiService.postReview(review)

                Log.i("UserApiVM", "Reseña publicada exitosamente: ${response.result}")

                _uiState.value = UiState.Ready

            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("MainViewModel (PostReview) http", e.message())
                        _uiState.value = UiState.Error(e.message())
                    }

                    else -> {
                        Log.i("MainViewModel (PostReview) else", e.toString())
                        _uiState.value = UiState.Error(e.toString())
                    }
                }
            }

        }
    }

    fun getAllUsersByCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading

                val response = ApiClient.apiService.getAllUsersByCategory(categoryId)
                val usersByCategory = response.usuarios

                Log.i("MainViewModel (GetAllUsersCategory) http", response.usuarios.toString())

                currentCategory = ""
                currentCategory = response.categoria

                Log.i("MainViewModel (GetAllUsersCategory) http", usersByCategory.toString())

                usersCategoryList.clear()
                usersCategoryList.addAll(usersByCategory)

                Log.i("MainViewModel (GetAllUsersCategory) http", usersByCategory.toString())

                _uiState.value = UiState.Ready

            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("MainViewModel (GetAllUsersCategory) http", e.message())
                        _uiState.value = UiState.Error(e.message())
                    }

                    else -> {
                        Log.i("MainViewModel (GetAllUsersCategory) else", e.toString())
                        _uiState.value = UiState.Error(e.toString())
                    }
                }
            }
        }
    }

    fun getTaskById() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading

                val response = ApiClient.apiService.getTasksById(taskId)
                /*Utilizar una variable global para pasarla a la pantalla*/


                _uiState.value = UiState.Ready

            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("MainViewModel (GetTask) http", e.message())
                        _uiState.value = UiState.Error(e.message())
                    }

                    else -> {
                        Log.i("MainViewModel (GetTask) else", e.toString())
                        _uiState.value = UiState.Error(e.toString())
                    }
                }
            }
        }
    }

    fun getAllNotificationsByUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading

                val response = ApiClient.apiService.getAllNotificationsByUser(currentUserId)
                val notifications = response.notifications

                usersNotificationsList.clear()
                usersNotificationsList.addAll(notifications)

                _uiState.value = UiState.Ready
            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("MainViewModel (GetNotifications) http", e.message())
                        _uiState.value = UiState.Error(e.message())
                    }

                    else -> {
                        Log.i("MainViewModel (GetNotifications) else", e.toString())
                        _uiState.value = UiState.Error(e.toString())
                    }
                }
            }
        }
    }

    fun createChatPreview(usuarioId: String, taskerId: String, taskName: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.createChatPreview(ChatPreviewApi(usuarioId, taskerId, taskName, ""))
                // Handle the response as needed
                Log.i("ChatPreview", "Chat preview created: ${response.result}")
            } catch (e: HttpException) {
                // Handle the error as needed
                Log.e("ChatPreview", "Error creating chat preview", e)
            }
        }
    }

    fun getChatPreviewsByUser(usuarioId: String) {
        viewModelScope.launch {
            try {
                val response: ApiChatPreviewsResponse = ApiClient.apiService.getChatPreviewsByUser(usuarioId)
                _mailbox.value = response.chatPreviews.map {
                    ApiUserByCategorySuccessful(
                        id = it.taskerId,
                        nombre = it.taskName,
                        fotoPerfil = "",
                        ubicacion = "",
                        usuarioTasker = true,
                        tarjetasAsociadas = emptyList(),
                        perfilTasker = DetallesPerfilTaskerCategory()
                    )
                }
                Log.i("ChatPreview", "Chat previews fetched: ${response.chatPreviews}")
            } catch (e: HttpException) {
                Log.e("ChatPreview", "Error fetching chat previews", e)
            }
        }
    }

    fun setStateToReady() {
        _uiState.value = UiState.Ready
    }


    fun doneUpdatingProfile() {
        _profileUpdated.value = false
    }
}

sealed class UiState {
    data object Loading : UiState()
    data object Ready : UiState()
    data class Success(val message: String) : UiState()
    data class Error(val message: String) : UiState()
}

fun ApiUserUpdatedSuccessful.toUserApiModel(): UserApiModel {
    return UserApiModel(
        id = this.id,
        nombre_completo = this.nombre,
        correo_electronico = this.correoElectronico,
        usuarioTasker = this.usuarioTasker,
        perfilTasker = this.perfilTasker,
        fotoPerfil = this.fotoPerfil,
        tarjetasAsociadas = this.tarjetasAsociadas,
        ubicacion = this.ubicacion
    )
}



