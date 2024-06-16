package com.owldevs.taskme.ui.navigation

import UserViewModel
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.ui.theme.TaskMeTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun MyBottomNav(navController: NavController = rememberNavController(), userViewModel: UserViewModel) {


    val currentRoute = navController.currentDestination?.route ?: " "
    val currentUser by userViewModel.currentUser.observeAsState()
    val role = currentUser?.role

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    ) {
        if (role == "client") {


            NavigationBarItem(
                selected = currentRoute == MainScreens.UserHome.route,
                onClick = { navController.navigate(MainScreens.UserHome.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = MainScreens.UserHome.icon),
                        contentDescription = "Home icon",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.size(28.dp)
                    )
                },
                label = {
                    Text(
                        text = MainScreens.UserHome.label,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

            )
        }else{
            NavigationBarItem(
                selected = currentRoute == MainScreens.TaskerHome.route,
                onClick = { navController.navigate(MainScreens.TaskerHome.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = MainScreens.TaskerHome.icon),
                        contentDescription = "Notifications icon",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.size(28.dp)
                    )
                },
                label = {
                    Text(
                        text = MainScreens.TaskerHome.label,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

            )
        }

        NavigationBarItem(
            selected = currentRoute == MainScreens.UserChat.route,
            onClick = { navController.navigate(MainScreens.UserChat.route) },
            icon = {
                Icon(
                    painter = painterResource(id = MainScreens.UserChat.icon),
                    contentDescription = "Chat icon",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.size(28.dp)
                )
            },
            label = {
                Text(
                    text = MainScreens.UserChat.label,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

        )

        NavigationBarItem(
            selected = currentRoute == MainScreens.UserTasks.route,
            onClick = { navController.navigate(MainScreens.UserTasks.route) },
            icon = {
                Icon(
                    painter = painterResource(id = MainScreens.UserTasks.icon),
                    contentDescription = "List icon",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.size(28.dp)
                )
            },
            label = {
                Text(
                    text = MainScreens.UserTasks.label,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

        )

        if(role=="client"){
            NavigationBarItem(
                selected = currentRoute == MainScreens.UserProfile.route,
                onClick = { navController.navigate(MainScreens.UserProfile.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = MainScreens.UserProfile.icon),
                        contentDescription = "Profile icon",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.size(28.dp)
                    )
                },
                label = {
                    Text(
                        text = MainScreens.UserProfile.label,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

            )
        }else{
            NavigationBarItem(
                selected = currentRoute == MainScreens.TaskerProfile.route,
                onClick = { navController.navigate(MainScreens.TaskerProfile.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = MainScreens.TaskerProfile.icon),
                        contentDescription = "Profile icon",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.size(28.dp)
                    )
                },
                label = {
                    Text(
                        text = MainScreens.TaskerProfile.label,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

            )
        }

    }


}

