package com.owldevs.taskme.ui.screens

import UserViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.components.CategoryCard

// for a 'val' variable
import androidx.compose.runtime.getValue

// for a `var` variable also add
import androidx.compose.runtime.setValue

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserHome(
    navController: NavController,
    userViewModel: UserViewModel = viewModel()
) {


    val currentUserState = userViewModel.currentUser.observeAsState()
    val currentUser = currentUserState.value
    val name = currentUser?.name

    // Lista de categorías con sus respectivos iconos (ID de recurso)
    val categories = listOf(
        "Electrician" to R.drawable.ic_bulb,
        "Plumber" to R.drawable.ic_plumbering,
        "Carpenter" to R.drawable.ic_carpentery,
        "Gardener" to R.drawable.ic_scissors,
        "Painter" to R.drawable.ic_paintroll
        // Agrega más categorías según sea necesario
    )

    // State for filter text and filtered categories
    var filterText by remember { mutableStateOf("") }

    val filteredCategories = if (filterText.isBlank()) {
        categories
    } else {
        categories.filter { (categoryName, _) ->
            categoryName.contains(filterText, ignoreCase = true)
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.img_homebg), // Replace with your image resource ID
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds // Scale the image to fill the bounds of the Box
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(0.3f)
                    .background(color = Color.Gray.copy(alpha = 0.5f))
                    .padding(bottom = 40.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Task Me!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Conectando habilidades, cumpliendo tareas",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = name ?: "No name",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Transparent
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "¿Qué necesitas?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                // TextField for category lookup
                TextField(
                    value = filterText,
                    onValueChange = { filterText = it },
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(horizontal = 16.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp)), // Adjust background color as needed
                    placeholder = { Text(text = "Buscar categoría...") },
                    singleLine = true, // Adjust based on your design,
                            textStyle = MaterialTheme.typography.bodyMedium,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
                    )

                )

                Spacer(modifier = Modifier.height(10.dp))

                // Debugging: Show current user role
                Text(
                    text = "Role: ${currentUser?.role ?: "No role"}",
                    fontSize = 16.sp,
                    color = Color.White
                )

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentWidth()
                ) {
                    FlowRow(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        filteredCategories.forEach { (categoryName, iconResId) ->
                            CategoryCard(
                                categoryName = categoryName,
                                categoryImg = iconResId,
                                navController = navController,
                                onClick = {
                                    navController.navigate("category/${categoryName}")
                                },
                            )
                            Spacer(modifier = Modifier.width(5.dp)) // Horizontal spacing between cards
                        }
                    }

                }

            }


        }

    }


}
