package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.owldevs.taskme.ui.components.UserCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScreen(navController: NavController) {
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
        ) {
            LazyColumn {
                itemsIndexed(listOf("Tarjeta 1", "Tarjeta 2")) { index, _ ->
                    UserCard(
                        isChecked = (selectedCardIndex == index),
                        onCheckedChange = {
                            selectedCardIndex = if (it) index else -1
                        }
                    )
                }
            }
            TextButton(
                onClick = {
                          /*NAVCONTROLLER ADD CARD*/
                    /*navController.navigate()*/
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
        }

    }
}

