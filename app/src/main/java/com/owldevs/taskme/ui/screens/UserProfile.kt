package com.owldevs.taskme.ui.screens

import UserViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.R
import com.owldevs.taskme.data.UserManager
import com.owldevs.taskme.ui.navigation.Screens

@Composable
fun UserProfile(navController: NavController = rememberNavController(), userViewModel: UserViewModel) {

    val currentUser by userViewModel.currentUser.observeAsState()

    fun maskPassword(password: String): String {
        return "*".repeat(password.length)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Perfil",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Icon(painter = painterResource(id = R.drawable.edit_square),
                    contentDescription = "Edit profile",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            // icon clicked todo
                        }
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pfp),
                    contentDescription = "Profile picture container",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            // icon clicked
                        }
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = currentUser?.name ?: "No name",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Correo:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = currentUser?.email ?: "No email",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Contraseña:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = currentUser?.password?.let { maskPassword(it) } ?: "No password",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                TextButton(onClick = {
                    navController.navigate(Screens.Card.route)
                }) {
                    Text(
                        text = "Mis tarjetas",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Debugging: Show current user role
                Text(
                    text = "Role: ${currentUser?.role ?: "No role"}",
                    fontSize = 16.sp,
                    color = Color.White
                )

                if (currentUser?.role == "client") {
                    Button(
                        onClick = {
                            userViewModel.changeUserRole("tasker")
                            UserManager.changeUserRole("tasker")
                        },
                        modifier = Modifier
                            .fillMaxWidth().padding(horizontal = 10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Cambiar a perfil de Tasker",
                            color = MaterialTheme.colorScheme.primaryContainer,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(8.dp))
                    }
                } else if (currentUser?.role == "tasker") {
                    Button(
                        onClick = {
                            userViewModel.changeUserRole("client")
                            UserManager.changeUserRole("client")
                        },
                        modifier = Modifier
                            .fillMaxWidth().padding(horizontal = 10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Cambiar a perfil de usuario",
                            color = MaterialTheme.colorScheme.primaryContainer,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        UserManager.logoutUser()
                        navController.navigate(Screens.Login.route) {
                            popUpTo(Screens.Login.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth().padding(horizontal = 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Cerrar sesión",
                        color = MaterialTheme.colorScheme.primaryContainer,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(8.dp))
                }

                Spacer(modifier = Modifier.height(30.dp))

                TextButton(onClick = {
                    // todo
                }) {
                    Text(text = "Ayuda",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.Underline)
                }
            }
        }
    }
}
