package com.owldevs.taskme.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.navigation.MainScreens
import com.owldevs.taskme.ui.navigation.SecondaryScreens

@Composable
fun OrderDetailBtn(navController: NavController){
    Button(onClick = {  navController.navigate(SecondaryScreens.ScheduleDetail.route) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary // Use the retrieved color
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_detail),
                contentDescription = "order detail",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Detalles de la tarea",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Conoce el estado de tu pedido",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }

}