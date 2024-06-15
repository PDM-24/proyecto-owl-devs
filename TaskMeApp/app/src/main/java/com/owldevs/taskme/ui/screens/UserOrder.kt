package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.owldevs.taskme.R

@Composable
fun UserOrder(navController: NavController) {
    val navy = colorResource(id = R.color.navy)
    val cyan = colorResource(id = R.color.cyan)


    Column {
        Box(modifier = Modifier.background(color = navy)) {
            Column(modifier = Modifier
                .background(color = cyan)
                .fillMaxWidth()
                .fillMaxHeight(0.2f)) {
                Text(
                    text = "Mis pedidos",
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.navy) // Convert color resource to Color object
                )
            }
        }

        Box(modifier = Modifier
            .background(color = navy)
        ){
            Column(){
                Button(onClick = {
                    /*navController.navigate()*/
                }) {
                    Text(text = "ver mas")
                }
            }
        }
    }

}