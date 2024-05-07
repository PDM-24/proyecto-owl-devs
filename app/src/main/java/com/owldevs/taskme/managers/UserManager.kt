package com.owldevs.taskme.managers

import com.owldevs.taskme.userdataprueba.User

object UserManager {

    private var currentUser: User? = null

    fun authenticateUser(email: String, password: String): Boolean {
        val validUser = User("John", "john@ex.com", "123", "client")
        if (email == validUser.email && password == validUser.password) {
            currentUser = validUser
            return true
        }
        return false
    }

    fun getCurrentUser(): User? {
        return currentUser
    }

    fun logoutUser() {
        currentUser = null
    }
}