package com.owldevs.taskme.ui.screens

import UserViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import com.owldevs.taskme.ui.navigation.Screens

@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {

    val navy = colorResource(id = R.color.navy)
    val cyan = colorResource(id = R.color.cyan)
    val latoBold = FontFamily(Font(R.font.lato_bold))

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.background(color = navy)) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(120.dp))

            // Add Image
            Icon(
                painter = painterResource(id = R.drawable.ic_taskme),
                contentDescription = "Emoji",
                tint = Color.Unspecified,
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = "Inicio de sesión",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = latoBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(text = "Correo", color = Color.White, fontFamily = latoBold, fontSize = 16.sp)

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "") }
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

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "") },
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
                            Screens.UserHome.createRoute(
                                currentUser?.email ?: "No email"
                            )
                        )
                    } else {
                        // Display an error message
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
                Text(text = "Ingresar", color = Color.Black, fontFamily = latoBold, fontSize = 18.sp, modifier = Modifier.padding(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { /* Navigate to sign-up screen */ }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Aún no tengo cuenta",
                        color = Color.White,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            TextButton(onClick = { /* Navigate to help and support */ }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Ayuda & soporte",
                        color = Color.White,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Or log in with")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(48.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google Img",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            // Handle Google login
                        }
                )
                Icon(painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Facebook Img",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            // Handle Facebook login
                        }
                )
            }
        }
    }
}
