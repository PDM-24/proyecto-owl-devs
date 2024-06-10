package com.owldevs.taskme.ui.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.ui.theme.BlueTM
import com.owldevs.taskme.ui.theme.TaskMeTheme

@Composable
fun MyBottomNav(navController: NavController = rememberNavController()) {
    val selected = remember { mutableStateOf(Icons.Default.Home) }

    // BottomAppBar with rounded corners and border
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .fillMaxWidth(1f)
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .border(
                BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            )
    ) {
        IconButton(
            onClick = {
                selected.value = Icons.Default.Home
                navController.navigate(Screens.UserHome.route) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.Home, contentDescription = null, modifier = Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.Home) Color.White else Color.DarkGray
            )
        }

        IconButton(
            onClick = {
                selected.value = Icons.Default.ShoppingCart
                navController.navigate(Screens.UserOrder.route) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.ShoppingCart) Color.White else Color.DarkGray
            )
        }

        IconButton(
            onClick = {
                selected.value = Icons.Default.Email
                navController.navigate(Screens.UserMailbox.route) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.Email, contentDescription = null, modifier = Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.Email) Color.White else Color.DarkGray
            )
        }

        IconButton(
            onClick = {
                selected.value = Icons.Default.AccountCircle
                navController.navigate(Screens.UserProfile.route) {
                    popUpTo(0)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(26.dp),
                tint = if (selected.value == Icons.Default.AccountCircle) Color.White else Color.DarkGray
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
