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
import com.owldevs.taskme.data.currentRole
import com.owldevs.taskme.data.currentUserId
import com.owldevs.taskme.data.currentUserRole
import com.owldevs.taskme.data.taskId
import com.owldevs.taskme.data.userTaskList
import com.owldevs.taskme.model.UserApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    private val userTaskList = MutableStateFlow<List<ApiTaskUserSuccessful>>(emptyList())


   fun getUserTasks(){
      // getRole(_currentUser.value)
       viewModelScope.launch(Dispatchers.IO){
           try {
               _uiState.value = UiState.Loading

               Log.i("Task adquire", currentUserId)
               Log.i("Task adquire", currentUserId)



               Log.i("Task adquire", currentUserRole)
             val response = ApiClient.apiService.getTaskbyUser(currentUserId,currentUserRole)
              // val response = ApiClient.apiService.getTaskbyUser("6670d91ba9935d6f0c50a7c7", "tasker")
               val taskList = response
               Log.i("Task adquire", taskList.toString())
               userTaskList.value = taskList
               Log.i("Task adquire", "Task añadidas exitosamente")
               _uiState.value = UiState.Ready
               //val response = ApiClient.apiService.getTaskbyUser("6670d91ba9935d6f0c50a7c7", "tasker")
           }catch(e: Exception){
               errorMessage = "Error al actualizar el perfil: ${e.message}"
               Log.e("UpdateProfile", "Error al actualizar el perfil", e)

           }

       }
   }


    fun filteredTasks(filter: String): StateFlow<List<ApiTaskUserSuccessful>> {
        return userTaskList.map { tasks ->
            if (filter.isEmpty()) {
                tasks
            } else {
                tasks.filter { it.estado == filter }
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }






}