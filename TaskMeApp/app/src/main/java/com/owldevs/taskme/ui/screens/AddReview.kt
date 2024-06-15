package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.owldevs.taskme.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import com.owldevs.taskme.ui.theme.NaranjaIntenso


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReview(
    navController: NavController

) {

    var currentRating by remember { mutableStateOf(0) }
    var comentario by remember { mutableStateOf("") }
    val cyan = colorResource(id = R.color.cyan)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Trabajo realizado") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { navController.popBackStack() }
                    )
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

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Spacer(modifier = Modifier.height(30.dp))

                Icon(
                    painter = painterResource(id = R.drawable.deco_svg_1),
                    contentDescription = "icono_decoracion",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Valorar trabajo:",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(20.dp))

                StarRating(
                    initialRating = currentRating,
                    onRatingChanged = { newRating ->
                        currentRating = newRating
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Puntuación: $currentRating/5",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(60.dp))

                Column() {
                    Text(
                        text = "Agregar comentario:",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    TextField(
                        value = comentario,
                        onValueChange = { comentario = it },
                        modifier = Modifier.width(300.dp),

                        textStyle = MaterialTheme.typography.bodyMedium,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }


                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(cyan),
                    modifier = Modifier
                        .width(300.dp)
                ) {
                    Text(text = "Publicar")
                }

                Spacer(modifier = Modifier.height(30.dp))


            }

        }
    }
}

@Composable
fun StarRating(
    modifier: Modifier = Modifier,
    totalStars: Int = 5,
    initialRating: Int = 0,
    onRatingChanged: (Int) -> Unit
) {
    var rating by remember { mutableIntStateOf(initialRating) }

    Row(modifier = modifier) {
        for (i in 1..totalStars) {
            Icon(
                painter = painterResource(id = R.drawable.ic_white_hammer),
                contentDescription = if (i <= rating) "Filled Star" else "Outlined Star",
                tint = if (i <= rating) MaterialTheme.colorScheme.tertiary else Color.Gray,
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        rating = i
                        onRatingChanged(rating)
                    }
            )
        }
    }
}
