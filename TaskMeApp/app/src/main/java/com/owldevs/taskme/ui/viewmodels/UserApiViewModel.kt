package com.owldevs.taskme.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owldevs.taskme.data.api.ApiClient
import com.owldevs.taskme.data.api.ApiUserSuccessful
import com.owldevs.taskme.model.UserApiModel
import com.owldevs.taskme.data.api.Habilidad
import com.owldevs.taskme.data.api.UserApi
import com.owldevs.taskme.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException


class UserApiViewModel : ViewModel() {
    private val _currentUser = MutableLiveData<UserApiModel?>()
    val currentUser: LiveData<UserApiModel?> = _currentUser

    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)

    fun setCurrentUser(userProfile: UserApiModel?) {
        _currentUser.value = userProfile
    }

    fun changeUserRole(role: String) {
        _currentUser.value = _currentUser.value?.copy(usuarioTasker = role == "tasker")
        Log.i("UserApiViewModel", "User role updated: ${_currentUser.value}")
    }

    fun updateProfile(name: String, email: String, bio: String?, categories: List<String>) {

        val updatedProfile = _currentUser.value?.copy(
            nombre = name,
            correoElectronico = email,
            perfilTasker = _currentUser.value?.perfilTasker?.copy(
                descripcion_personal = bio ?: "",
                habilidades = categories.map { Habilidad(nombre = it) }
            )
        )
        _currentUser.value = updatedProfile
        // Add logic to save the updated profile to the server or database
    }


    fun updateUser(user: UserApi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = UiState.Loading
                val response = user.correo_electronico.let { validId ->
                    ApiClient.apiService.updateUser(validId, user)
                }
                Log.i("UserApiViewModel", response.toString())
                _uiState.value = UiState.Success("Usuario actualizado correctamente")

            } catch (e: Exception) {
                when (e) {
                    is HttpException -> {
                        Log.i("UserApiViewModel", e.message())
                        _uiState.value = UiState.Error( e.message())
                    }

                    else -> {
                        Log.i("UserApiViewModel", e.toString())
                        _uiState.value = UiState.Error( "Error. Contacte con el servicio de soporte")


                    }
                }
            }
        }
    }

}

sealed class UiState {
    data object Loading : UiState()
    data object Ready : UiState()
    data class Success (val msg : String) : UiState()
    data class Error (val msg : String) : UiState()
}



