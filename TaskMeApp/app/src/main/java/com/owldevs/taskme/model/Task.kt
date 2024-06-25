package com.owldevs.taskme.model


data class Task (
    val userId: String,
    val category: String,
    val date: String,
    val location: String,
    val time: String,
    val price: Double,
    var status: String = "Pendiente"
)
