package com.owldevs.taskme.ui.screens

import UserViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import com.owldevs.taskme.R
import com.owldevs.taskme.data.UserManager
import com.owldevs.taskme.ui.navigation.MainScreens

@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {

    val navy = colorResource(id = R.color.navy)
    val cyan = colorResource(id = R.color.cyan)
    val latoBold = FontFamily(Font(R.font.lato_bold))

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.background(color = navy)) {
        Row{
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onBackground)
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            item {
                Spacer(modifier = Modifier.height(100.dp))

                // Add Image
                Icon(
                    painter = painterResource(id = R.drawable.ic_taskme),
                    contentDescription = "Emoji",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(200.dp)
                )

                Text(
                    text = "Inicio de sesión",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(text = "Correo",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground)

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text(text = "",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary ) },
                            textStyle = MaterialTheme.typography.bodyMedium,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(text = "Contraseña", color = Color.White, fontFamily = latoBold, fontSize = 16.sp)

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(text = "",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary ) },
                            textStyle = MaterialTheme.typography.bodyMedium,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary),
                            visualTransformation = PasswordVisualTransformation()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val isAuthenticated = UserManager.authenticateUser(email, password)
                        if (isAuthenticated) {
                            val currentUser = UserManager.getCurrentUser()
                            userViewModel.setCurrentUser(currentUser)
                            navController.navigate(
                                MainScreens.UserHome.route
                            )
                        } else {
                            // Display an error message todo
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp, end = 50.dp, top = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = cyan
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Ingresar",
                        color = Color.Black,
                        fontFamily = latoBold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = {
                    // todo
                }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Aún no tengo cuenta",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "O ingresar con",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(48.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google Img",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                // Handle Google login
                            }
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Facebook Img",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                // Handle Facebook login
                            }
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                TextButton(onClick = {
                    //todo
                }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Ayuda & soporte",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
