package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.navigation.SecondaryScreens
import com.owldevs.taskme.ui.theme.AzulMarino

@Composable
fun RegisterClientOrTaskScreen(navController: NavController) {

    val cyan = colorResource(id = R.color.cyan)
    val latoBold = FontFamily(Font(R.font.lato_bold))

    var showPopup by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color= AzulMarino)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_backbtn),
                    contentDescription = "Back",
                    modifier = Modifier.clickable { navController.navigateUp() } // Volver a la pantalla anterior correctamente
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_help),
                    contentDescription = "Help",
                    modifier = Modifier.clickable { showPopup = true } // Mostrar el popup
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_taskme),
                contentDescription = "Task Me Logo",
                modifier = Modifier.size(200.dp)
            )
           /* Text(
                text = "Task Me!",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )*/
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate(SecondaryScreens.RegisterClient.route)}, // Navegar a la pantalla RegisterClient
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp, top = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = cyan
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Registrarme como cliente",
                    color = Color.Black,
                    fontFamily = latoBold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                    )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Handle Register as Tasker */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp, top = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = cyan
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Registrarme como Tasker",
                    color = Color.Black,
                    fontFamily = latoBold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Text(
            text = "Ayuda & Soporte",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .clickable { navController.navigate(SecondaryScreens.Support.route)} // Navegar a la pantalla de soporte
        )

        if (showPopup) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .background(Color(0xFF2D9CDB), shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                        .width(250.dp) // Ajusta el ancho del popup
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.weight(1f)) // Espacio entre la X y el contenido
                        Text(
                            text = "X",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { showPopup = false }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Cliente: Usuario que únicamente podrá buscar ayuda dentro de la aplicación.",
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tasker: Usuario que podrá brindar ayuda en base a sus habilidades dentro de la aplicación.",
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}


