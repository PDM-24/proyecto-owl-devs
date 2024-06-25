package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.R
import com.owldevs.taskme.data.userReviewsList
import com.owldevs.taskme.ui.components.ExpandedReviewCard
import com.owldevs.taskme.ui.theme.TaskMeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewsScreen(
    navController: NavController,
    userRatings: String = "John Doe"
) {
    var isExpanded by remember { mutableStateOf(false) }
    var ratingValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Reseñas: $userRatings",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(32.dp)
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
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_taskme),
                contentDescription = "Task Logo",
                modifier = Modifier.size(112.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                ) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(
                            text = "Calificaciones:",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                Box(
                    modifier = Modifier.width(150.dp)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = isExpanded,
                        onExpandedChange = { isExpanded = !isExpanded }) {
                        OutlinedTextField(
                            value = when (ratingValue) {
                                "5" -> "5 Estrellas"
                                "4" -> "4 Estrellas"
                                "3" -> "3 Estrellas"
                                "2" -> "2 Estrellas"
                                "1" -> "1 Estrellas"
                                else -> ""
                            },
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = isExpanded
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "# Estrellas",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            },
                            label = {
                                Text(
                                    text = "Reseñas",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            },
                            singleLine = true,
                            textStyle = MaterialTheme.typography.bodySmall,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = isExpanded,
                            onDismissRequest = { isExpanded = false }) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "5 Estrellas",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                },
                                onClick = {
                                    ratingValue = "5"
                                    isExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "4 Estrellas",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                },
                                onClick = {
                                    ratingValue = "4"
                                    isExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "3 Estrellas",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                },
                                onClick = {
                                    ratingValue = "3"
                                    isExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "2 Estrellas",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                },
                                onClick = {
                                    ratingValue = "2"
                                    isExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "1 Estrellas",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                },
                                onClick = {
                                    ratingValue = "1"
                                    isExpanded = false
                                }
                            )
                        }
                    }
                }
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                if (userReviewsList.isNotEmpty()) {
                    items(userReviewsList) { review ->
                        ExpandedReviewCard(
                            userName = review.autorId.nombre,
                            reviewBody = review.texto,
                            reviewDate = review.fecha.time,
                            userRating = review.calificacion.toDouble()
                        )
                    }
                } else {
                    item {
                        Text(
                            text = "Este usuario aun no tiene ninguna reseña",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            textDecoration = TextDecoration.Underline,
                            textAlign = TextAlign.Justify
                        )
                    }
                }

            }
        }
    }
}