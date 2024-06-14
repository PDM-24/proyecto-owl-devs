package com.owldevs.taskme.ui.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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

@Composable
fun MyBottomNav(navController: NavController = rememberNavController()) {
    val selectedIconResId = remember { mutableStateOf(R.drawable.ic_home) }

    // BottomAppBar with rounded corners and border

        BottomAppBar(
            containerColor = BlueTM,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .border(
                    BorderStroke(2.dp, Color.White),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                ).background(Color.Transparent)
        ) {
            IconButton(
                onClick = {
                    selectedIconResId.value = R.drawable.ic_home
                    navController.navigate(Screens.UserHome.route) {
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
                    navController.navigate(Screens.UserOrder.route) {
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
                    navController.navigate(Screens.UserMailbox.route) {
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
                    navController.navigate(Screens.UserProfile.route) {
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
        }
    }


@Preview(showSystemUi = true)
@Composable
fun ItemPreview(){
    TaskMeTheme(darkTheme = true) {
        MyBottomNav()
    }
}
