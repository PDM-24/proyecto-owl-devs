package com.owldevs.taskme.ui.navigation

import UserViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.owldevs.taskme.ui.screens.*
import com.owldevs.taskme.ui.viewmodels.ChatViewModel

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
        containerColor = Color.Transparent,
        bottomBar = {
            // Ocultar la barra de navegación en la página de login
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
                LoginScreen(navController, userViewModel = userViewModel)
            }
            composable(route = Screens.UserProfile.route) {
                UserProfile(navController, userViewModel = userViewModel)
            }
            composable(route = Screens.UserHome.route) {
                UserHome(navController, userViewModel = userViewModel)
            }
            composable(route = Screens.UserOrder.route) {
                UserOrder()
            }
            composable(route = Screens.UserMailbox.route) {
                UserMailbox(navController)
            }
            composable(
                route = "chat_screen/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.StringType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getString("userId") ?: return@composable
                val chatViewModel = viewModel<ChatViewModel>()
                ChatScreen(navController, chatViewModel, userViewModel = userViewModel, userId)
            }
        }
    }
}
