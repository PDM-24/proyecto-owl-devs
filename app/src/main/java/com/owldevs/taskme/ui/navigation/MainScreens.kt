package com.owldevs.taskme.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import com.owldevs.taskme.R

/*sealed class Screens (val screen: String) {
    data object UserHome: Screens("user_home")
    data object UserOrder: Screens("user_order")
    data object UserMailbox: Screens("user_mailbox")
    data object UserProfile: Screens("user_profile")
} */

//Rutas principales de la barra inferior
sealed class MainScreens(val route: String, val label: String, val icon: Int) {

    data object UserHome: MainScreens("userHome", "Home", R.drawable.ic_home)

    data object TaskerHome: MainScreens("taskerHome", "Home", R.drawable.ic_bell_notifications)

    data object UserProfile: MainScreens("userProfile", "Profile", R.drawable.ic_pfp)

    data object UserChat: MainScreens("userChat", "Agenda", R.drawable.ic_chats)

    data object UserTasks: MainScreens("userTasks", "Tasks", R.drawable.ic_orders)

}