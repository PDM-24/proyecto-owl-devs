package com.owldevs.taskme.data

import com.owldevs.taskme.model.User

object UserManager {

    private var currentUser: User? = null

    private val validUser = User("A", "a@ex.com", "1", "client")

    fun authenticateUser(email: String, password: String): Boolean {
        return if (email == validUser.email && password == validUser.password) {
            currentUser = validUser
            true
        } else {
            false
        }
    }

    fun getCurrentUser(): User? {
        return currentUser
    }

    fun logoutUser() {
        currentUser = null
    }

    fun changeUserRole(role: String) {
        currentUser?.let {
            currentUser = it.copy(role = role)
        }
    }
}
