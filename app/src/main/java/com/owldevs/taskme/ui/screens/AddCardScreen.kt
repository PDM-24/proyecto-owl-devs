package com.owldevs.taskme.ui.screens

import UserViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.components.FundsScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(navController: NavController, userViewModel: UserViewModel) {
    val cyan = colorResource(id = R.color.cyan)
    val currentUser by userViewModel.currentUser.observeAsState()
    val role = currentUser?.role
    var namecard by remember {
        mutableStateOf("")
    }
    var cardnumber by remember {
        mutableStateOf("")
    }
    var expedetiondate by remember {
        mutableStateOf("")
    }
    var cvcnumber by remember {
        mutableStateOf("")
    }
    var isChecked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Agregar tarjeta de crédito o débito",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
           Column(
               modifier = Modifier
                   .fillMaxHeight()
                   .padding(50.dp)
           ) {
               Box (
                   modifier = Modifier.fillMaxWidth(),
                   contentAlignment = Alignment.Center
               ) {
                   TextField(
                       value = namecard,
                       onValueChange = {namecard = it},
                       label = { Text(text = "Nombre tarjetahabiente") }
                   )
               }
               Spacer(modifier = Modifier.height(16.dp))
               Box(
                   modifier = Modifier.fillMaxWidth(),
                   contentAlignment = Alignment.Center
               ){
                   TextField(
                       value = cardnumber,
                       onValueChange = {cardnumber = it},
                       label = { Text(text = "Numero de la tarjeta de credito o debito",fontSize = 12.sp) },
                       keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                       )
               }

               Spacer(modifier = Modifier.height(16.dp))
               Box(
                   modifier = Modifier.fillMaxWidth(),
                   contentAlignment = Alignment.Center
               ){
                   Row(
                       horizontalArrangement = Arrangement.SpaceBetween
                   ) {
                       TextField(
                           value = expedetiondate,
                           onValueChange = {expedetiondate = it},
                           label = { Text(text = "MM/YY") },
                           modifier = Modifier.weight(1f),
                           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                       )
                       Spacer(modifier = Modifier.width(16.dp))
                       TextField(
                           value = cvcnumber,
                           onValueChange = {cvcnumber = it},
                           label = { Text(text = "CVV/CVC") },
                           modifier = Modifier.weight(1f),
                           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                           visualTransformation = PasswordVisualTransformation()
                       )
                   }
               }
               if (role == "tasker"){
                   Spacer(modifier = Modifier.height(16.dp))
                   FundsScreen()
               }
               Spacer(modifier = Modifier.height(16.dp))
               Box (
                   contentAlignment = Alignment.Center
               ) {
                   Row (
                       modifier = Modifier
                           .padding(top = 30.dp),
                       horizontalArrangement = Arrangement.SpaceBetween
                   ){
                       Checkbox(
                           checked = isChecked,
                           onCheckedChange = { isChecked = it }
                       )
                       Box(
                           contentAlignment = Alignment.Center
                       ) {
                           Text(text = "Guardar tarjeta para futuros pagos", fontSize = 17.sp)
                       }
                   }

               }
               Spacer(modifier = Modifier.height(16.dp))
               Box(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(start = 20.dp)
                   ,
                   contentAlignment = Alignment.Center
               ){
                   Button(
                       onClick = { /*TODO*/ }  ,
                       colors = ButtonDefaults.buttonColors(
                           containerColor = cyan
                       ), modifier = Modifier
                           .fillMaxWidth()
                           .height(45.dp) ,
                       shape = RoundedCornerShape(10.dp)
                   ) {
                       Text(text = "Agregar tarjeta")
                   }
               }
           }
        }
    }
}
