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
import com.owldevs.taskme.data.api.ApiTaskUserSuccessful
import com.owldevs.taskme.model.UserApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskApiViewModel : ViewModel(){
    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    var uiState: StateFlow<UiState> = _uiState

    var role = "cliente"

    private val _currentUser = MutableLiveData<UserApiModel?>()
    var currentUser: LiveData<UserApiModel?> = _currentUser

    private val _tasks = MutableLiveData<List<ApiTaskUserSuccessful>>()
    val tasks: LiveData<List<ApiTaskUserSuccessful>> = _tasks

    var errorMessage by mutableStateOf("")
        private set

    fun getRole(userProfile: UserApiModel?) {
        _currentUser.value = userProfile
        if (_currentUser.value!!.usuarioTasker == true) {
            role = "tasker"
        }else{
            role = "cliente"

        }
    }


   fun getUserTasks(){
      // getRole(_currentUser.value)
       viewModelScope.launch(Dispatchers.IO){
           try {
              // _uiState.value = UiState.Loading
              // val response = ApiClient.apiService.getTaskbyUser(_currentUser.value?.id, role)
               val response = ApiClient.apiService.getTaskbyUser("6670d91ba9935d6f0c50a7c7", "tasker")
               Log.i("Task adquire", "holaaaa")
               Log.i("Task adquire", response.toString())

              // _tasks.value = response // Esto establece la lista de tareas en el LiveData
           }catch(e: Exception){
               errorMessage = "Error al actualizar el perfil: ${e.message}"
               Log.e("UpdateProfile", "Error al actualizar el perfil", e)

           }

       }
   }
}