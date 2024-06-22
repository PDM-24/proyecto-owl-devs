package com.owldevs.taskme.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owldevs.taskme.data.api.ApiClient
import com.owldevs.taskme.data.api.ApiUserSuccessful
import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.model.UserApiModel
import com.owldevs.taskme.data.api.Habilidad
import com.owldevs.taskme.data.api.LoginRequest
import com.owldevs.taskme.model.UpdateUserRequest
import kotlinx.coroutines.launch
import java.io.IOException


class UserApiViewModel : ViewModel() {
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
                val response = ApiClient.apiService.updateUser(_currentUser.value?.id, updatedProfile)
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
                                descripcion_personal = _currentUser.value?.perfilTasker?.descripcion_personal ?: ""
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
}





