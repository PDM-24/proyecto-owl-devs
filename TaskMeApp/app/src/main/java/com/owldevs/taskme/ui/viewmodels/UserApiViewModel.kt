package com.owldevs.taskme.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.owldevs.taskme.model.UserApiModel

class UserApiViewModel : ViewModel() {

    private val _currentUser = MutableLiveData<UserApiModel?>()
    val currentUser: LiveData<UserApiModel?> = _currentUser

    fun setCurrentUser(user: UserApiModel?) {
        _currentUser.value = user
    }

    fun changeUserRole(role: String) {
        _currentUser.value = _currentUser.value?.copy(usuarioTasker = role == "tasker")
    }
}
