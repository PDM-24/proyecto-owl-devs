package com.owldevs.taskme.ui.navigation

/*sealed class Screens (val screen: String) {
    data object UserHome: Screens("user_home")
    data object UserOrder: Screens("user_order")
    data object UserMailbox: Screens("user_mailbox")
    data object UserProfile: Screens("user_profile")
} */

sealed class Screens(val route: String) {
    data object Login : Screens("login")
    data object UserHome : Screens("user_home/{email}") {
        fun createRoute(email: String) = "user_home/$email"
    }
    data object UserOrder : Screens("user_order")
    data object UserMailbox : Screens("user_mailbox")
    data object UserProfile : Screens("user_profile/{email}") {
        fun createRoute(email: String) = "user_profile/$email"
    }
    data object Chat : Screens("chat/{userId}") {
        fun createRoute(userId: String) = "chat/$userId"
    }
}
