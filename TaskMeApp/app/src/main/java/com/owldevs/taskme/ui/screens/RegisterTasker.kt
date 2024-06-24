package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.navigation.SecondaryScreens

@Composable
fun RegisterTaskerScreen(navController: NavController, onBackClick: () -> Unit = {}) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C2A39))
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
                    fontSize = 24.sp
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
                    Text(text = "Nombre", color = Color.White)
                    CustomTextFieldTasker(value = name, onValueChange = { name = it }, placeholder = "Nombre")

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Apellido", color = Color.White)
                    CustomTextFieldTasker(value = surname, onValueChange = { surname = it }, placeholder = "Apellido")

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Correo", color = Color.White)
                    CustomTextFieldTasker(value = email, onValueChange = { email = it }, placeholder = "Correo")

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Contraseña", color = Color.White)
                    CustomTextFieldTasker(value = password, onValueChange = { password = it }, placeholder = "Contraseña", isPassword = true)

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Confirmar contraseña", color = Color.White)
                    CustomTextFieldTasker(value = confirmPassword, onValueChange = { confirmPassword = it }, placeholder = "Confirmar contraseña", isPassword = true)

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Ubicación", color = Color.White)
                    CustomTextFieldTasker(value = location, onValueChange = { location = it }, placeholder = "Ubicación", trailingIcon = {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.locationicon), contentDescription = "Location", tint = Color.Gray)
                    })

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Button(
                onClick = { /* Handle registration */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF00D4FF))
            ) {
                Text(text = "Registrarme", color = Color.White)
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
                        color = Color.White,
                        modifier = Modifier.clickable { navController.navigate(SecondaryScreens.LoginScreen.route) } // Navegar a la pantalla de login
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Ayuda&Soporte",
                        color = Color.White,
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
    trailingIcon: @Composable (() -> Unit)? = null
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
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
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}
