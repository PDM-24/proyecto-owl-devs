package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.owldevs.taskme.ui.components.FundsScreen
import com.owldevs.taskme.ui.components.PayButtonComponent
import com.owldevs.taskme.ui.components.UserCard
import com.owldevs.taskme.ui.navigation.SecondaryScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentCardScreen(navController: NavController){
    var selectedCardIndex by remember { mutableStateOf(-1) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Mis tarjetas",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
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
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                listOf("Tarjeta 1", "Tarjeta 2").forEachIndexed { index, _ ->
                UserCard(
                    isChecked = (selectedCardIndex == index),
                    onCheckedChange = {
                        selectedCardIndex = if (it) index else -1
                    }
                )
                }
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    TextButton(
                        onClick = {
                            navController.navigate(SecondaryScreens.AddCardScreen.route)
                        },
                        modifier = Modifier
                            .padding(top = 13.dp, start = 20.dp)
                    ) {
                        Text(
                            text = "+ Agregar tarjeta",
                            color = Color.White,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                    FundsScreen()
                    PayButtonComponent(navController = navController)
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }

    }

}