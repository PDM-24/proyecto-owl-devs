package com.owldevs.taskme.ui.navigation

import UserViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
        //containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            // Ocultar la barra de navegación en la página de login

            if (currentRoute != SecondaryScreens.LoginScreen.route) {

                MyBottomNav(navController, userViewModel)
            }
        }
    ) {
        NavHost(
            navController = navController,

            startDestination = SecondaryScreens.LoginScreen.route,
            modifier = Modifier.padding(it)
        ) {

            /*RUTA DEFAULT*/
            composable(route = SecondaryScreens.LoginScreen.route) {
                LoginScreen(navController, userViewModel)
            }

            /*RUTAS PRINCIPALES*/

            composable(route = MainScreens.UserProfile.route) {
                UserProfile(navController, userViewModel)
            }


            composable(route = MainScreens.TaskerProfile.route) {
                ProfileScreen(navController, userViewModel)
            }


            composable(route = MainScreens.UserHome.route) {
                UserHome(navController, userViewModel)
            }
            composable(route = MainScreens.TaskerHome.route) {
                NotificationsScreen()
            }
            composable(route = MainScreens.UserChat.route) {
                UserMailbox(navController)
            }
            composable(route = MainScreens.UserTasks.route) {
                TasksScreen(navController)
            }
            /*FIN DE RUTAS PRINCIPALES*/

            /*RUTAS SECUNDARIAS*/

            composable(route = SecondaryScreens.UserOrder.route) {
                UserOrder(navController)
            }
            composable(route = SecondaryScreens.UserMailbox.route) {
                UserMailbox(navController)
            }
            composable(route = SecondaryScreens.TaskScreen.route) {
                UserTaskScreen(navController)
            }
            composable(
                route = SecondaryScreens.ChatScreen.route
            ) {
                val chatViewModel = viewModel<ChatViewModel>()
                ChatScreen(navController, chatViewModel, userViewModel)
            }
            composable(SecondaryScreens.ScheduleScreen.route) {

                ScheduleTaskScreen(navController = navController, userViewModel = userViewModel)
            }
            composable(SecondaryScreens.ScheduleDetail.route) {
                ScheduleTaskDetail(navController = navController, userViewModel = userViewModel)

            }
            composable(SecondaryScreens.AddCardScreen.route){
                AddCardScreen(navController = navController, userViewModel = userViewModel)
            }

            composable(SecondaryScreens.CategoryScreen.route) {
                CategoryScreen(navController)
            }
            composable(SecondaryScreens.FAQScreen.route) {
                FAQScreen(navController)
            }
            composable(SecondaryScreens.ReviewsScreen.route) {
                ReviewsScreen(navController)
            }
            composable(SecondaryScreens.UserSettings.route) {
                UserSettingsScreen(navController, userViewModel)
            }
            composable(SecondaryScreens.EditProfile.route) {
                EditProfile(navController = navController, userViewModel = userViewModel)

            }

            composable(SecondaryScreens.AddTaskToProfile.route) {
                AddTaskToProfile(navController = navController)
            }

            composable(SecondaryScreens.AddReview.route) {
                AddReview(navController = navController)
            }
            composable(SecondaryScreens.UserFunds.route){
                UserFundsScreen(navController)
            }
            composable(SecondaryScreens.PaymentCardScreen.route){
                PaymentCardScreen(navController)
            }

            composable(SecondaryScreens.TypeOfPaymentScreen.route){
                TypeOfPaymentScreen(navController)
            }

            composable(SecondaryScreens.SuccesfulPaymentScreen.route){
                SuccesfulPaymentScreen(navController)

            }

            composable(SecondaryScreens.TaskerInfoScreen.route){
                TaskerInfoScreen(navController, userViewModel)

            }

            /*FIN DE RUTAS SECUNDARIAS*/
        }
    }
}
