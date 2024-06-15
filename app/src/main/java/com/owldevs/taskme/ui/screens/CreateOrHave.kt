package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.navigation.Screens
import com.owldevs.taskme.ui.theme.AzulMarino
import com.owldevs.taskme.ui.theme.NaranjaIntenso

@Composable
fun CreateOrHaveScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AzulMarino) // Color de fondo
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_taksme),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Task Me!",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(color = NaranjaIntenso) // Color de fondo de Task Me!
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f)) // Espacio para empujar la columna hacia abajo

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)) // Esquinas superiores redondeadas
                    .background(color = AzulMarino) // Color azul oscuro
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { navController.navigate(Screens.RegisterClientOrTask.route)}, // Navegar a la pantalla de creación de cuenta
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFAEFFF6)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Crear una cuenta", fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(8.dp)) // Espacio entre el texto y el icono
                            Image(
                                painter = painterResource(id = R.drawable.ic_backbtn),
                                contentDescription = "Icono",
                                modifier = Modifier
                                    .size(24.dp)
                                    .graphicsLayer(rotationZ = 180f), // Rotar el icono 180 grados
                                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color(0xFF121F34)) // Aplicar color azul al icono
                            )
                        }
                    }

                    ClickableText(
                        text = AnnotatedString("Ya tengo una cuenta"),
                        onClick = { navController.navigate(Screens.Login.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(1f).height(1.dp).background(Color.White))
                        Text(
                            text = "ó",
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f).height(1.dp).background(Color.White))
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, bottom = 32.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "Google",
                            modifier = Modifier.size(48.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_facebook),
                            contentDescription = "Facebook",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }
    }
}


