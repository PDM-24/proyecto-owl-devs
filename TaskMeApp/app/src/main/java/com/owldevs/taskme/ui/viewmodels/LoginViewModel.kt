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
                        id = response.id,
                        correo_electronico = response.correoElectronico,
                        nombre_completo = response.nombre,
                        fotoPerfil = response.fotoPerfil,
                        ubicacion = response.ubicacion,
                        usuarioTasker = response.usuarioTasker, // Set usuarioTasker to false by default
                        tarjetasAsociadas = response.tarjetasAsociadas,
                        perfilTasker = response.perfilTasker

                        /*DetallesPerfilTaskerModel(
                                telefono = response.perfilTasker!!.telefono,
                                descripcion_personal = response.perfilTasker.descripcion_personal,
                                fecha_union = response.perfilTasker?.fecha_union,
                                trabajos_realizados = response.perfilTasker?.trabajos_realizados,
                                promedio_calificaciones = response.perfilTasker?.promedio_calificaciones,
                                habilidades = response.perfilTasker?.habilidades,
                                galeria_trabajos = response.perfilTasker?.galeria_trabajos

                            )*/
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


