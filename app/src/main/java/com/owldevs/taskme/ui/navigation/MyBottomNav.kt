package com.owldevs.taskme.ui.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.theme.BlueTM
import com.owldevs.taskme.ui.theme.TaskMeTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.navigation.Navigation

@Composable
fun MyBottomNav(navController: NavController = rememberNavController()) {


    val currentRoute = navController.currentDestination?.route ?: " "

    // BottomAppBar with rounded corners and border

    /*BottomAppBar(
        containerColor = BlueTM,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .border(
                BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .background(Color.Transparent)
    ) {
        IconButton(
            onClick = {
                selectedIconResId.value = R.drawable.ic_home
                navController.navigate(MainScreens.UserHome.route) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "home",
                tint = if (selectedIconResId.value == R.drawable.ic_home) Color.DarkGray else Color.White,
                modifier = Modifier.size(36.dp)
            )
        }

        IconButton(
            onClick = {
                selectedIconResId.value = R.drawable.ic_orders
                navController.navigate(MainScreens.UserOrder.route) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_orders),
                contentDescription = "order detail",
                tint = if (selectedIconResId.value == R.drawable.ic_orders) Color.DarkGray else Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
        IconButton(
            onClick = {
                selectedIconResId.value = R.drawable.ic_chats
                navController.navigate(MainScreens.UserMailbox.route) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chats),
                contentDescription = "order detail",
                tint = if (selectedIconResId.value == R.drawable.ic_chats) Color.DarkGray else Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
        IconButton(
            onClick = {
                selectedIconResId.value = R.drawable.ic_pfp
                navController.navigate(MainScreens.UserProfile.route) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pfp),
                contentDescription = "order detail",
                tint = if (selectedIconResId.value == R.drawable.ic_pfp) Color.DarkGray else Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }*/

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    ) {

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

    }


}


@Preview(showSystemUi = true)
@Composable
fun ItemPreview() {
    TaskMeTheme(darkTheme = true) {
        MyBottomNav()
    }
}
