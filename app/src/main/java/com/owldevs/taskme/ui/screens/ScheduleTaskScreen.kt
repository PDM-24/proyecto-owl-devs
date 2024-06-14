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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.navigation.Screens
import com.owldevs.taskme.ui.viewmodels.TaskViewModel
import com.owldevs.taskme.ui.viewmodels.ChatViewModel

@Composable
fun ScheduleTaskScreen(
    navController: NavHostController,
    taskViewModel: TaskViewModel = viewModel(),
    chatViewModel: ChatViewModel = viewModel(),
    userViewModel: UserViewModel
) {
    val currentUser by userViewModel.currentUser.observeAsState()
    val name = currentUser?.name ?: "Unknown"
    var category by remember { mutableStateOf("Plomero") }
    var date by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("San Salvador, San Salvador") }
    var time by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Text(text = "Agendar tarea", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 30.dp, bottom = 10.dp, start = 20.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Campos de entrada para la información de la tarea

        Row(modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
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
            Column(){
                Text( text = "Ubicación", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(3.dp))
                TextField(
                    value = location ,
                    onValueChange = { location = it },
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

            Column(){
                Text( text = "Establece una fecha", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(3.dp))
                TextField(
                    value = date ,
                    onValueChange = { date = it },
                    label = { Text("DD/MM/YYYY", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onPrimary ) },
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

            Column(){
                Text( text = "Establece una hora (recuerda colocarlo en formato 24h)", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(3.dp))
                TextField(
                    value = time ,
                    onValueChange = { time = it },
                    label = { Text("23:59",
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

            Column() {
                Text(
                    text = "Establece un precio para la tarea",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(3.dp))
                TextField(
                    value = price.toString(),
                    onValueChange = { price = it.toDoubleOrNull() ?: 0.0 },
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

        Spacer(modifier = Modifier.height(26.dp))

        Button(
            onClick = {
                taskViewModel.updateTask(name, category, date, location, time, price)
                taskViewModel.scheduleTask()
                navController.navigate(Screens.UserHome.route)
            },
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Agendar pedido",
                color = MaterialTheme.colorScheme.primaryContainer,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp))
        }
    }
}
