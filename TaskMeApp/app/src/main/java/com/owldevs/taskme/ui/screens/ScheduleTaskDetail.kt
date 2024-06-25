package com.owldevs.taskme.ui.screens

import UserViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.navigation.MainScreens
import com.owldevs.taskme.ui.navigation.SecondaryScreens
import com.owldevs.taskme.ui.theme.TaskMeTheme
import com.owldevs.taskme.ui.viewmodels.TaskViewModel
import com.owldevs.taskme.ui.viewmodels.ChatViewModel

@Composable
fun ScheduleTaskDetail(
    navController: NavHostController,
    taskViewModel: TaskViewModel = viewModel(),
    chatViewModel: ChatViewModel = viewModel(),
    userViewModel: UserViewModel
) {
    val currentUser by userViewModel.currentUser.observeAsState()
    val name = currentUser?.name ?: "Unknown"

    var showDialog by remember { mutableStateOf(false) }

    var category by remember { mutableStateOf("Plomero") }
    var date by remember { mutableStateOf("25/06/2024") }
    var location by remember { mutableStateOf("San Salvador, San Salvador") }
    var time by remember { mutableStateOf("18:00") }
    var price by remember { mutableStateOf(0.0) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false },

                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    shape = RoundedCornerShape(10.dp)) {
                    Text("Sí, cancelar tarea",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    // Handle task cancellation here todo
                },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),

                    shape = RoundedCornerShape(10.dp)) {
                    Text("No, salir",
                        color = MaterialTheme.colorScheme.primaryContainer,
                        style = MaterialTheme.typography.bodyMedium)
                }
            },

            text = { Text("¿Deseas cancelar la tarea?",
                color = MaterialTheme.colorScheme.primaryContainer,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center)},
            modifier = Modifier.padding(16.dp),
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Tarea", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center ,modifier = Modifier.padding(top = 30.dp, bottom = 10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.tertiary)
                .padding(vertical = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {  navController.navigate(SecondaryScreens.ScheduleDetail.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(10.dp)
            ) {

                Text(
                    text = "Regresar al chat",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    style = MaterialTheme.typography.titleMedium
                )

            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Campos de entrada para la información de la tarea

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)){
            Row(modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start){
                Icon(
                    painter = painterResource(id = R.drawable.ic_white_hammer),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text( text = "<Categoría>", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start){
                Icon(
                    painter = painterResource(id = R.drawable.ic_pfp),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text( text = name, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start){
                Icon(
                    painter = painterResource(id = R.drawable.locationicon),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text( text = location, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)

            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start){
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text( text = date, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)

            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start){
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text( text = time, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start){
                Icon(
                    painter = painterResource(id = R.drawable.moneyicon),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text( text = price.toString(), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)

            }

        }


        Spacer(modifier = Modifier.height(26.dp))

        Button(
            onClick = {
                navController.navigate(SecondaryScreens.TypeOfPaymentScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Proceder al pago",
                color = MaterialTheme.colorScheme.primaryContainer,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                showDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Cancelar tarea",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp))
        }
    }
}