package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.R
import com.owldevs.taskme.data.categoryId
import com.owldevs.taskme.ui.components.CategoryCard
import com.owldevs.taskme.ui.viewmodels.CategoryViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserHome(
    navController: NavController,
    categoryViewModel: CategoryViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        categoryViewModel.fetchCategories()
    }

    val categories by categoryViewModel.categories.observeAsState(emptyList())

    // State for filter text and filtered categories
    var filterText by remember { mutableStateOf("") }

    val filteredCategories = if (filterText.isBlank()) {
        categories
    } else {
        categories.filter { category ->
            category.nombre.contains(filterText, ignoreCase = true)
        }
    }

    // Group categories into pairs
    val groupedCategories = filteredCategories.chunked(2)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.img_homebg),
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds
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

                TextField(
                    value = filterText,
                    onValueChange = { filterText = it },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(horizontal = 16.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
                    placeholder = { Text(text = "Buscar categoría...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            items(groupedCategories) { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    pair.forEachIndexed { index, category ->
                        CategoryCard(
                            categoryName = category.nombre,
                            categoryImg = R.drawable.ic_carpentery, // Replace with actual icon if available
                            navController = navController,
                            onClick = {
                                navController.navigate("category/${category.nombre}")
                                categoryId = category.id
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = if (index == 0) 8.dp else 0.dp) // Add space only for the first card
                        )
                    }
                    // Add a spacer if there's only one item in the row
                    if (pair.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }
}
