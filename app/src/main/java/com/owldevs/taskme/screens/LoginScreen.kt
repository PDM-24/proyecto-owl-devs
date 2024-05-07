package com.owldevs.taskme.screens

import UserViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.* // cambié el "remember" por "*" para que se importe todito de una
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
import androidx.compose.ui.text.style.TextDecoration
import com.owldevs.taskme.R
import com.owldevs.taskme.managers.UserManager



@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel) {

    val navy = colorResource(id = R.color.navy)
    val cyan = colorResource(id = R.color.cyan)

    var email by remember {
        mutableStateOf(" ")
    }

    var password by remember {
        mutableStateOf(" ")
    }
    Box(modifier = Modifier.background(color = navy)) {
        Column(
            modifier = Modifier
                .fillMaxSize(),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { // para evitar que los elementos overlappeen

            Spacer(modifier = Modifier.height(120.dp))

            // agregar imagen
            Image(
                painter = painterResource(id = R.drawable.ic_taskme),
                contentDescription = "Login Img",
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = "Inicio de sesión",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )


            //text fields
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center // Corrected line
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(text = "Correo", color = Color.White)

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
                contentAlignment = Alignment.Center // Corrected line
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(text = "Contraseña", color = Color.White)

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "") }, // para ocultar la password
                        visualTransformation = PasswordVisualTransformation()
                    )
                }
            }

            // button
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val isAuthenticated = UserManager.authenticateUser(email, password)
                    if (isAuthenticated) {
                        val currentUser = UserManager.getCurrentUser()
                        userViewModel.currentUser = currentUser
                        navController.navigate(Screens.UserHome.createRoute(currentUser?.email ?: "No email"))
                    } else {
                        // Mostrar un error
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp, top = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = cyan // Use the retrieved color
                )
            ) {
                Text(text = "Ingresar", color = Color.Black)
            }

            // forgot password
            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { }
            ) {
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

            TextButton(onClick = { }
            ) {
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

            /* También puede ser:
            Text(text = "Forgot password?", modifier = Modifier.clickable{})
             */

            // log in w/ dif platforms
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Or log in with")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(48.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google Img",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            // icon clicked
                        }
                )
                Image(painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google Img",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            // icon clicked
                        }
                )
            }
        }

    }


}