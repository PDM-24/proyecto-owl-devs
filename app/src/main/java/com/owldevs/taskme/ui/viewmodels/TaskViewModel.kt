package com.owldevs.taskme.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owldevs.taskme.model.Task
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    var task = Task(
        userId = "",
        category = "",
        date = "",
        location = "",
        time = "",
        price = 0.0
    )
        private set

    fun updateTask(
        userId: String,
        category: String,
        date: String,
        location: String,
        time: String,
        price: Double
    ) {
        task = task.copy(
            userId = userId,
            category = category,
            date = date,
            location = location,
            time = time,
            price = price
        )
    }

    fun scheduleTask() {
        viewModelScope.launch {
            task = task.copy(status = "En proceso")
            // Aquí puedes añadir la lógica para guardar la tarea en una base de datos o hacer otra operación
        }
    }
}