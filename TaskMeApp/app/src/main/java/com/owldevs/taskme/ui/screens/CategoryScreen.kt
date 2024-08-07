package com.owldevs.taskme.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.R
import com.owldevs.taskme.data.categoryId
import com.owldevs.taskme.data.currentUserId
import com.owldevs.taskme.data.usersCategoryList
import com.owldevs.taskme.ui.components.UserInfoCard
import com.owldevs.taskme.ui.navigation.MyBottomNav
import com.owldevs.taskme.ui.theme.TaskMeTheme
import com.owldevs.taskme.ui.viewmodels.UserApiViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navController: NavController,
    categoryName: String = "CategoryName",
    taskerDirection: String = "San Salvador, El Salvador",
    userApiViewModel: UserApiViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        userApiViewModel.getAllUsersByCategory()
    }

    var userSearch by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
    var ratingValue by remember { mutableStateOf("") }

    // Filtrar usuarios por calificación y nombre
    val filteredUsers = usersCategoryList.filter { user ->
        val matchesRating = if (ratingValue.isNotEmpty()) {
            user.perfilTasker.promedioCalificaciones >= ratingValue.toDouble()
        } else {
            true
        }
        val matchesName = user.nombre.contains(userSearch, ignoreCase = true)
        matchesRating && matchesName
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(152.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_homebg),
                contentDescription = "Category Img",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(0f)
            )
            LargeTopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .zIndex(1f),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(
                            text = categoryName,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSecondary,
                            modifier = Modifier
                                .zIndex(1f)
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .zIndex(1f)
                            .size(28.dp)
                            .clickable {
                                navController.popBackStack()
                                categoryId = ""
                            }
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.zIndex(1f)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location Icon")
            Text(
                text = taskerDirection,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textDecoration = TextDecoration.Underline
            )
        }

        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = userSearch,
            onValueChange = { userToSearch -> userSearch = userToSearch },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon"
                )
            },
            label = {
                Text(text = "Buscar Tasker", style = MaterialTheme.typography.titleMedium)
            },
            placeholder = {
                Text(
                    text = "Jhon Doe",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                focusedLabelColor = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ) {
            Text(
                text = "Taskers destacados en tu área:",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded }
            ) {
                OutlinedTextField(
                    value = when (ratingValue) {
                        "5" -> "5 Estrellas"
                        "4" -> "4 Estrellas"
                        "3" -> "3 Estrellas"
                        "2" -> "2 Estrellas"
                        "1" -> "1 Estrellas"
                        else -> "Filtrar por Calificación"
                    },
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(
                            text = "Filtrar por Calificación",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }
                ) {
                    DropdownMenuItem(text = {
                        Text(text = "5 Estrellas", style = MaterialTheme.typography.titleSmall)
                    }, onClick = {
                        ratingValue = "5"
                        isExpanded = false
                    })
                    DropdownMenuItem(text = {
                        Text(text = "4 Estrellas", style = MaterialTheme.typography.titleSmall)
                    }, onClick = {
                        ratingValue = "4"
                        isExpanded = false
                    })
                    DropdownMenuItem(text = {
                        Text(text = "3 Estrellas", style = MaterialTheme.typography.titleSmall)
                    }, onClick = {
                        ratingValue = "3"
                        isExpanded = false
                    })
                    DropdownMenuItem(text = {
                        Text(text = "2 Estrellas", style = MaterialTheme.typography.titleSmall)
                    }, onClick = {
                        ratingValue = "2"
                        isExpanded = false
                    })
                    DropdownMenuItem(text = {
                        Text(text = "1 Estrellas", style = MaterialTheme.typography.titleSmall)
                    }, onClick = {
                        ratingValue = "1"
                        isExpanded = false
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(bottom = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (true) {
                Arrangement.spacedBy(12.dp)
            } else {
                Arrangement.Center
            }
        ) {
            if (filteredUsers.isNotEmpty()) {
                items(filteredUsers) { user ->
                    UserInfoCard(
                        navController,
                        user = user
                    )
                }
            } else {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay Taskers en esta ubicación",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
    }
}

