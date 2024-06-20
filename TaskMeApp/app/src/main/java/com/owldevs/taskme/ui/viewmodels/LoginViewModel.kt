package com.owldevs.taskme.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owldevs.taskme.data.api.ApiClient
import com.owldevs.taskme.data.api.LoginRequest
import com.owldevs.taskme.model.UserApiResponse
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var loginState by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.loginUser(loginRequest)
                Log.i("LoginViewModel", "Login response: $response")

                // Verificar si la respuesta indica un inicio de sesión exitoso
                if (response != null) {
                    // Aquí adaptas la respuesta del servidor a tu modelo de datos en el cliente
                    val userProfile = UserApiResponse(
                        correoElectronico = response.correoElectronico,
                        nombre = response.nombre,
                        ubicacion = response.ubicacion,
                        usuarioTasker = response.usuarioTasker,
                        tarjetasAsociadas = response.tarjetasAsociadas,
                        perfilTasker = response.perfilTasker
                    )
                    Log.i("LoginViewModel", "User profile: $userProfile")
                    loginState = true // Indicar que el inicio de sesión fue exitoso
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
