package com.owldevs.taskme.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.owldevs.taskme.model.UserApiModel
import com.owldevs.taskme.data.api.Habilidad


class UserApiViewModel : ViewModel() {
    private val _currentUser = MutableLiveData<UserApiModel?>()
    val currentUser: LiveData<UserApiModel?> = _currentUser

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
}


