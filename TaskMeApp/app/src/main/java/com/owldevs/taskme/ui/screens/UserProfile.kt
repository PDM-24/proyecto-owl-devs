package com.owldevs.taskme.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.R
import com.owldevs.taskme.data.UserManager
import com.owldevs.taskme.ui.navigation.MainScreens
import com.owldevs.taskme.ui.navigation.SecondaryScreens
import com.owldevs.taskme.ui.theme.TaskMeTheme
import com.owldevs.taskme.ui.viewmodels.UserApiViewModel

@Composable
fun UserProfile(
    navController: NavController,
    userApiViewModel: UserApiViewModel = viewModel()
) {


    val currentUser by userApiViewModel.currentUser.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
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
                Icon(
                    painter = painterResource(id = R.drawable.edit_square),
                    contentDescription = "Edit profile",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            navController.navigate(SecondaryScreens.EditProfile.route)
                        }
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                    text = currentUser?.nombre_completo ?: "No name",
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
                        text = currentUser?.correo_electronico ?: "No email",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                TextButton(onClick = { /* Navigate to card screen */ }) {
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
                    .padding(horizontal = 30.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (currentUser?.perfilTasker?.habilidades.isNullOrEmpty()) {
                    Button(
                        onClick = {
                            navController.navigate(SecondaryScreens.UsertoTasker.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Convertirme en Tasker",
                            color = MaterialTheme.colorScheme.primaryContainer,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                }

                else {
                    Button(
                        onClick = {
                            userApiViewModel.changeUserRole("tasker")
                            navController.navigate(MainScreens.TaskerProfile.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Cambiar a perfil de Tasker",
                            color = MaterialTheme.colorScheme.primaryContainer,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }


                }


                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate(SecondaryScreens.LoginScreen.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Cerrar sesión",
                        color = MaterialTheme.colorScheme.primaryContainer,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                TextButton(onClick = { /* Handle Help Click */ }) {
                    Text(
                        text = "Ayuda",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}


