package com.owldevs.taskme.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owldevs.taskme.data.api.ApiClient
import com.owldevs.taskme.data.api.ApiUserSuccessful
import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.model.UserApiModel
import com.owldevs.taskme.data.api.Habilidad
import com.owldevs.taskme.data.api.LoginRequest
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.HttpException
import java.io.IOException


class UserApiViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    var uiState: StateFlow<UiState> = _uiState

    private val _currentUser = MutableLiveData<UserApiModel?>()
    val currentUser: LiveData<UserApiModel?> = _currentUser

    var errorMessage by mutableStateOf("")
        private set


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
                Log.i("Updated Profile", "Login response: $response")

                if (response != null) {
                    // Log para cada campo
                    Log.i("Updated Profile", "Nombre: ${response.nombre}")
                    Log.i("Updated Profile", "Correo: ${response.correoElectronico}")
                    Log.i("Updated Profile", "Foto: ${response.fotoPerfil}")
                    Log.i("Updated Profile", "Habilidades: ${response.habilidades}")


                    if (response.nombre != null && response.correoElectronico != null && response.fotoPerfil != null && response.habilidades != null) {
                        _currentUser.value = _currentUser.value?.copy(
                            nombre_completo = response.nombre,
                            correo_electronico = response.correoElectronico,
                            fotoPerfil = response.fotoPerfil,
                            perfilTasker = _currentUser.value?.perfilTasker?.copy(
                                habilidades = response.habilidades
                            ) ?: DetallesPerfilTasker(
                                habilidades = response.habilidades,
                                descripcion_personal = _currentUser.value?.perfilTasker?.descripcion_personal
                                    ?: ""
                            )
                        )
                    } else {
                        errorMessage = "Error: La respuesta contiene campos nulos"
                    }
                } else {
                    errorMessage = "Error: Respuesta nula del servidor"
                }
            } catch (e: Exception) {
                errorMessage = "Error al actualizar el perfil: ${e.message}"
                Log.e("LoginViewModel", "Error al iniciar sesión", e)
            }
        }
    }

    fun getAllReviewsByUser(taskerId: String) {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                _uiState.value = UiState.Loading

                Log.i("UserApiVM", taskerId)

                val response = ApiClient.apiService.getAllReviewsByUser(taskerId)
                val reviewsList = response.reviews

                Log.i("UserApiVM", response.reviews.toString())

                userReviewsList.clear()
                userReviewsList.addAll(reviewsList)

                Log.i("UserApiVM", "Reseñas obtenidas exitosamente")
                _uiState.value = UiState.Ready

            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("MainViewModel (GetAllReviews) http", e.message())
                        _uiState.value = UiState.Error(e.message())
                    }

                    else -> {
                        Log.i("MainViewModel (GetAllReviews) else", e.toString())
                        _uiState.value = UiState.Error(e.toString())
                    }
                }
            }

        }
    }

    fun postReview(review: ReviewSchemaApi) {
        viewModelScope.launch(Dispatchers.IO) {

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

    fun setStateToReady() {
        _uiState.value = UiState.Ready
    }
}

sealed class UiState {
    data object Loading : UiState()
    data object Ready : UiState()
    data class Success(val message: String) : UiState()
    data class Error(val message: String) : UiState()
}





