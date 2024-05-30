package com.owldevs.taskme.model

data class User (
    val name: String,
    val email: String,
    val password: String,
    var usertype: String // tasker o cliente
)