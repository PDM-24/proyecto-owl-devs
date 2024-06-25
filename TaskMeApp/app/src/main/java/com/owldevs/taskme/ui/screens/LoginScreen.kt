package com.owldevs.taskme.ui.screens

import android.util.Log
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.data.api.LoginRequest
import com.owldevs.taskme.ui.navigation.MainScreens
import com.owldevs.taskme.ui.navigation.SecondaryScreens
import com.owldevs.taskme.ui.viewmodels.LoginViewModel
import com.owldevs.taskme.ui.viewmodels.UserApiViewModel

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel(), userApiViewModel: UserApiViewModel = viewModel()) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by loginViewModel::loginState
    val errorMessage by loginViewModel::errorMessage

    if (loginState) {
        LaunchedEffect(Unit) {
            navController.navigate(MainScreens.UserHome.route)
        }
    }




    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { navController.navigateUp() }
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))

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
                        Text(
                            text = "Correo",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            modifier = Modifier.width(300.dp),
                            value = email,
                            onValueChange = { email = it },
                            label = {
                                Text(
                                    text = "",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            },
                            textStyle = MaterialTheme.typography.bodyMedium,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Contraseña",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            modifier = Modifier.width(300.dp),
                            value = password,
                            onValueChange = { password = it },
                            label = {
                                Text(
                                    text = "",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            },
                            textStyle = MaterialTheme.typography.bodyMedium,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            visualTransformation = PasswordVisualTransformation()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Button(
                    onClick = {
                        val loginRequest =
                            LoginRequest(correoElectronico = email, contrasenia = password)
                        Log.i("LoginViewModel", "Login request: $loginRequest")
                        loginViewModel.loginUser(loginRequest, userApiViewModel) // Pass userApiViewModel here
                        Log.i("LoginViewModel", "Login request: $loginRequest")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp, end = 50.dp, top = 30.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Ingresar",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = {
                    // todo
                    navController.navigate(SecondaryScreens.RegisterClientOrTask.route)
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

                Text(
                    text = "O ingresar con",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

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
                    navController.navigate(SecondaryScreens.Support.route)
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


