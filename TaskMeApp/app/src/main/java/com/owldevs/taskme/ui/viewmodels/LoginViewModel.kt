package com.owldevs.taskme.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owldevs.taskme.data.api.ApiClient
import com.owldevs.taskme.data.api.LoginRequest
import com.owldevs.taskme.model.UserApiModel
import com.owldevs.taskme.model.UserApiResponse
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var loginState by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    private val _userProfile = MutableLiveData<UserApiModel?>()
    val userProfile: LiveData<UserApiModel?> = _userProfile

    fun loginUser(loginRequest: LoginRequest, userApiViewModel: UserApiViewModel) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.loginUser(loginRequest)
                Log.i("LoginViewModel", "Login response: $response")

                if (response != null) {
                    val userProfile = UserApiModel(
                        correoElectronico = response.correoElectronico,
                        nombre = response.nombre,
                        ubicacion = response.ubicacion,
                        usuarioTasker = false, // Set usuarioTasker to false by default
                        tarjetasAsociadas = response.tarjetasAsociadas,
                        perfilTasker = response.perfilTasker
                    )
                    _userProfile.value = userProfile
                    userApiViewModel.setCurrentUser(userProfile)
                    loginState = true
                } else {
                    errorMessage = "Error: Respuesta nula del servidor"
                }
            } catch (e: Exception) {
                errorMessage = "Error al iniciar sesión: ${e.message}"
                Log.e("LoginViewModel", "Error al iniciar sesión", e)
            }
        }
    }
}


