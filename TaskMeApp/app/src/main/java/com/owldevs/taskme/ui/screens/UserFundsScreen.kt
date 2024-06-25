package com.owldevs.taskme.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.components.UserFundsContent
import com.owldevs.taskme.ui.navigation.SecondaryScreens

@Composable
fun UserFundsScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                )
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { navController.popBackStack() }
                )
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "More Information",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { /* more information*/ }
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    text = "Fondos",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$0.00",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Button(
                    onClick = { navController.navigate(SecondaryScreens.PaymentCardScreen.route) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = "Realizar pago")
                }
            }
        }

        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Movimientos",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )

                IconButton(onClick = { expanded = !expanded }) {
                    Image(
                        painter =
                        if (expanded) painterResource(id = R.drawable.arrowdropdown_btn) else painterResource(
                            id = R.drawable.arrowdropdownleft_btn
                        ),
                        contentDescription = if (expanded) "arrow down" else "arrow down"
                    )
                }
            }
            AnimatedVisibility(visible = expanded) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        UserFundsContent()
                        UserFundsContent()
                        UserFundsContent()
                        UserFundsContent()
                        UserFundsContent()
                    }

                }

            }
        }
    }
}