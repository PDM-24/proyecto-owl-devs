package com.owldevs.taskme.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun FundsScreen(){
    var cardfunds by remember {
        mutableStateOf("")
    }

    Column {
        Text(text = "Ingrese la cantidad que desea agregar a sus fondos:")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = cardfunds,
            onValueChange = {cardfunds = it},
            label = { Text(text = "$0.00") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}