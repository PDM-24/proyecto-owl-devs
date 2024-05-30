package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.owldevs.taskme.R

@Composable
fun UserMailbox(){

    val navy = colorResource(id = R.color.navy)

    Box(modifier = Modifier.background(color = navy)){
        Column {
            Text(text = "Bandeja de entrada")
        }
    }
}