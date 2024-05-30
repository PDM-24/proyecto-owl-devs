package com.owldevs.taskme.ui.navigation

import UserViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.ui.screens.LoginScreen
import com.owldevs.taskme.ui.screens.UserHome
import com.owldevs.taskme.ui.screens.UserMailbox
import com.owldevs.taskme.ui.screens.UserOrder
import com.owldevs.taskme.ui.screens.UserProfile

@Composable
fun MyAppNavigation() {
    val navController = rememberNavController()
    val userViewModel = viewModel<UserViewModel>()

    // State to track the current screen route
    var currentRoute by remember { mutableStateOf<String?>(null) }

    // Listen for navigation changes
    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute = destination.route
        }
    }

    Scaffold(
        bottomBar = {
            // Hide the bottom bar on the login screen
            if (currentRoute != Screens.Login.route) {
                MyBottomNav(navController)
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screens.Login.route,
            modifier = Modifier.padding(it)
        ) {
            composable(route = Screens.Login.route) {
                LoginScreen(navController, userViewModel  = userViewModel)
            }
            composable(route = Screens.UserProfile.route) {
                UserProfile(navController)
            }
            composable(route = Screens.UserHome.route) {
                UserHome(navController)
            }
            composable(route = Screens.UserOrder.route) {
                UserOrder()
            }
            composable(route = Screens.UserMailbox.route) {
                UserMailbox()
            }
        }
    }
}
