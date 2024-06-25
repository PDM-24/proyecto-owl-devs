package com.owldevs.taskme.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.owldevs.taskme.ui.navigation.SecondaryScreens

@Composable
fun PayButtonComponent(navController: NavController){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 33.dp)
            .height(50.dp),
        shape = RoundedCornerShape(5.dp),
        onClick = { navController.navigate(SecondaryScreens.SuccesfulPaymentScreen.route) }
    ) {
        Text(text = "Pagar")
    }
}