package com.owldevs.taskme.ui.screens

import UserViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.R
import com.owldevs.taskme.data.api.ApiUserByCategorySuccessful
import com.owldevs.taskme.data.currentTasker
import com.owldevs.taskme.data.taskerId
import com.owldevs.taskme.data.userReviewsList
import com.owldevs.taskme.ui.components.AbilityChip
import com.owldevs.taskme.ui.components.ReducedReviewCard
import com.owldevs.taskme.ui.navigation.SecondaryScreens
import com.owldevs.taskme.ui.viewmodels.UserApiViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TaskerInfoScreen(
    navController: NavController = rememberNavController(),
    userApiViewModel: UserApiViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        userApiViewModel.getAllReviewsByUser(currentTasker.id)
    }

    var isExpanded by remember { mutableStateOf(false) }
    var ratingValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
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
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            navController.popBackStack()
                            taskerId.value = ""
                            currentTasker = ApiUserByCategorySuccessful()
                        }
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_pfp),
                contentDescription = "User Img",
                modifier = Modifier.size(86.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = currentTasker.nombre,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Trabajos realizados: ${currentTasker.perfilTasker.trabajosRealizados}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        Column(
            modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Biografia: ",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = currentTasker.perfilTasker.descripcionPersonal,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 5
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = "Contactar", style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Soy:",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    maxItemsInEachRow = 4
                ) {
                    currentTasker.perfilTasker.habilidades.forEach { habilidad ->
                        habilidad.nombre?.let { AbilityChip(abilityName = it) }
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Trabajos realizados:",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_pic),
                        contentDescription = "Trabajo realizado",
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_pic),
                        contentDescription = "Trabajo realizado",
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_pic),
                        contentDescription = "Trabajo realizado",
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_pic),
                        contentDescription = "Trabajo realizado",
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_pic),
                        contentDescription = "Trabajo realizado",
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp)
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                    ) {
                        TextButton(onClick = { navController.navigate(SecondaryScreens.ReviewsScreen.route) }) {
                            Text(
                                text = "Reseñas: ${currentTasker.perfilTasker.promedioCalificaciones}",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Filled.Build,
                                contentDescription = "Reviews",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier.width(150.dp)
                    ) {
                        ExposedDropdownMenuBox(expanded = isExpanded,
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
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                },
                                singleLine = true,
                                textStyle = MaterialTheme.typography.bodyMedium,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.menuAnchor()
                            )
                            ExposedDropdownMenu(expanded = isExpanded,
                                onDismissRequest = { isExpanded = false }) {
                                DropdownMenuItem(text = {
                                    Text(
                                        text = "5 Estrellas",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                }, onClick = {
                                    ratingValue = "5"
                                    isExpanded = false
                                })
                                DropdownMenuItem(text = {
                                    Text(
                                        text = "4 Estrellas",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                }, onClick = {
                                    ratingValue = "4"
                                    isExpanded = false
                                })
                                DropdownMenuItem(text = {
                                    Text(
                                        text = "3 Estrellas",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                }, onClick = {
                                    ratingValue = "3"
                                    isExpanded = false
                                })
                                DropdownMenuItem(text = {
                                    Text(
                                        text = "2 Estrellas",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                }, onClick = {
                                    ratingValue = "2"
                                    isExpanded = false
                                })
                                DropdownMenuItem(text = {
                                    Text(
                                        text = "1 Estrellas",
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                }, onClick = {
                                    ratingValue = "1"
                                    isExpanded = false
                                })
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = { navController.navigate(SecondaryScreens.AddReview.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.edit_square),
                            contentDescription = "Write review",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Escribir reseña",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
                if (userReviewsList.isNotEmpty()) {
                    userReviewsList.forEach { review ->
                        ReducedReviewCard(
                            navController,
                            userName = review.autorId.nombre,
                            reviewBody = review.texto,
                            reviewDate = review.fecha.time,
                            userRating = review.calificacion.toDouble()
                        )
                    }
                } else {
                    Text(
                        text = "Este usuario aun no tiene ninguna reseña",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}