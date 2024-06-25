package com.owldevs.taskme.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.owldevs.taskme.R
import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.data.api.UserApi
import com.owldevs.taskme.ui.navigation.SecondaryScreens
import com.owldevs.taskme.ui.theme.AzulMarino

@Composable
fun RegisterTaskerScreen(navController: NavController, onBackClick: () -> Unit = {}) {

    LaunchedEffect(Unit) {
        Log.i("RegisterTask"," Check!")
    }

    val cyan = colorResource(id = R.color.cyan)
    val latoBold = FontFamily(Font(R.font.lato_bold))

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var about_you by remember { mutableStateOf("") }
    var habilidades by remember { mutableStateOf(listOf<String>()) }
    var habilidadesExpanded by remember { mutableStateOf(false) }

    val habilidadesOptions = listOf(
        "Medición y corte de madera",
        "Instalación de puertas y ventanas",
        "Uso de herramientas eléctricas",
        "Mantenimiento preventivo de vehículos",
        "Conocimiento de rutas y navegación",
        "Instalación y reparación de tuberías",
        "Manejo de residuos y reciclaje",
        "Pintura de interiores y exteriores",
        "Reparación de grietas y agujeros en paredes",
        "Podado de árboles y arbustos",
        "Uso de herramientas de jardinería",
        "Embalaje y protección de objetos",
        "Organización y planificación de mudanzas",
        "Instalación y reparación de sistemas eléctricos",
        "Mantenimiento de sistemas de iluminación"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AzulMarino)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Task Me!",
                    color = Color.White,
                    fontSize = 32.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_backbtn),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable { onBackClick() }
                        .size(24.dp)
                )
            }

            Text(
                text = "Registro",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Nombre", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    CustomTextFieldTasker(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = "Nombre"
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Apellido", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    CustomTextFieldTasker(
                        value = surname,
                        onValueChange = { surname = it },
                        placeholder = "Apellido"
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Correo", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    CustomTextFieldTasker(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = "Correo"
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Contraseña", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    CustomTextFieldTasker(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = "Contraseña",
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Confirmar contraseña", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    CustomTextFieldTasker(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        placeholder = "Confirmar contraseña",
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Ubicación", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    CustomTextFieldTasker(
                        value = location,
                        onValueChange = { location = it },
                        placeholder = "Ubicación",
                        trailingIcon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.locationicon),
                                contentDescription = "Location",
                                tint = Color.Gray
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Número de telefono", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    CustomTextFieldTasker(
                        value = telefono,
                        onValueChange = { telefono = it },
                        placeholder = "Número de telefono"
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Acerca de ti:", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    CustomTextFieldTasker(
                        value = about_you,
                        onValueChange = { about_you = it },
                        placeholder = "Cuentanos sobre ti", isMultiline = true,
                        maxLines = 5)

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Habilidades:", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = MaterialTheme.shapes.small)
                        .clickable { habilidadesExpanded = !habilidadesExpanded }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (habilidades.isEmpty()) "Selecciona tus habilidades" else habilidades.joinToString(),
                                color = if (habilidades.isEmpty()) Color.Gray else Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.arrowdropdown_btn),
                                contentDescription = "Dropdown",
                                tint = Color.Gray
                            )
                        }
                    }
                    if (habilidadesExpanded) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = MaterialTheme.shapes.small)
                            .padding(16.dp)
                        ) {
                            habilidadesOptions.forEach { option ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            if (habilidades.contains(option)) {
                                                habilidades = habilidades - option
                                            } else {
                                                habilidades = habilidades + option
                                            }
                                        }
                                ) {
                                    Checkbox(
                                        checked = habilidades.contains(option),
                                        onCheckedChange = {
                                            if (it) {
                                                habilidades = habilidades + option
                                            } else {
                                                habilidades = habilidades - option
                                            }
                                        }
                                    )
                                    Text(text = option, fontSize = 16.sp, color = Color.Black)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Button(
                onClick = {
                    /*val user = DetallesPerfilTasker(
                        telefono = "",
                        descripcion_personal = "",
                        fecha_union = "",
                        trabajos_realizados = 0,
                        promedio_calificaciones = 0,
                        foto = "",
                        habilidades = listOf(),
                        galeria_trabajos = listOf()
                )*/

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
                    text = "Registrarme",
                    color = Color.Black,
                    fontFamily = latoBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Ya tengo una cuenta",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { navController.navigate(SecondaryScreens.LoginScreen.route) } // Navegar a la pantalla de login
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Ayuda & Soporte",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { navController.navigate(SecondaryScreens.Support.route) } // Navegar a la pantalla de soporte
                    )
                }
            }
        }
    }
}

@Composable
fun CustomTextFieldTasker(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    isMultiline: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    BasicTextField(
        value = value,
        onValueChange = {
            if (it.lines().size <= maxLines) {
                onValueChange(it)
            }
        },
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isMultiline) 120.dp else 56.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.small)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                if (value.isEmpty()) {
                    Text(text = placeholder, color = Color.Gray, fontSize = 16.sp)
                }
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        innerTextField()
                    }
                    if (trailingIcon != null) {
                        trailingIcon()
                    }
                }
            }
        },
        singleLine = !isMultiline,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}


