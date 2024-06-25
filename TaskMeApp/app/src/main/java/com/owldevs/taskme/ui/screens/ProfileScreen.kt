package com.owldevs.taskme.ui.screens

import android.provider.ContactsContract.Profile
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
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.components.AbilityChip
import com.owldevs.taskme.ui.components.ReducedReviewCard
import com.owldevs.taskme.ui.navigation.SecondaryScreens
import com.owldevs.taskme.ui.viewmodels.UserApiViewModel
import coil.compose.rememberImagePainter
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    userApiViewModel: UserApiViewModel = viewModel()
) {
    val currentUser by userApiViewModel.currentUser.observeAsState()

    // Extract data from currentUser
    val userName = currentUser?.nombre_completo ?: "Unknown"
    val taskerProfile = currentUser?.perfilTasker
    val tasksCompleted = taskerProfile?.trabajos_realizados ?: 0
    val userBio = taskerProfile?.descripcion_personal ?: "No bio available"
    val ratingMedia = taskerProfile?.promedio_calificaciones ?: 0
    val taskerFounds = 0 // Assuming tasker funds to be 0 for now, as it's not present in DetallesPerfilTasker

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
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { navController.navigate(SecondaryScreens.UserSettings.route) }
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_pfp),
                contentDescription = "User Img",
                modifier = Modifier.size(86.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Trabajos realizados: $tasksCompleted",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { navController.navigate(SecondaryScreens.UserFunds.route) }) {

                        Icon(
                            imageVector = Icons.Filled.AddCircle,
                            contentDescription = "Founds",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "Fondos: $ $taskerFounds",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

            }
        }
        Column(
            modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "Biografia: ",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = userBio,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 5
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "Me dedico a:",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    maxItemsInEachRow = 4
                ) {
                    taskerProfile?.habilidades?.forEach { habilidad ->
                        AbilityChip(name = habilidad.nombre ?: "Unknown")
                    }
                }
            }
            Column(
                modifier = Modifier.padding(horizontal = 15.dp),
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
                    taskerProfile?.galeria_trabajos?.forEach { trabajo ->
                        Image(
                            painter = rememberImagePainter(data = trabajo.url),
                            contentDescription = "Trabajo realizado",
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.Start
                    ) {
                            Text(
                                text = "Reseñas",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable { navController.navigate(SecondaryScreens.ReviewsScreen.route) }
                            )
                             Spacer(modifier = Modifier.width(8.dp))
                            Text(
                            text = "$ratingMedia",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Filled.Build,
                                contentDescription = "Reviews",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                    }
                    Box(
                        modifier = Modifier.width(150.dp),
                        contentAlignment = Alignment.BottomCenter
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
                                modifier = Modifier
                                    .menuAnchor()
                                    .width(150.dp)
                                    .height(48.dp)
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

                ReducedReviewCard(navController)
                ReducedReviewCard(navController)
                ReducedReviewCard(navController)
                ReducedReviewCard(navController)
                ReducedReviewCard(navController)
                ReducedReviewCard(navController)
            }
        }
    }
}

@Composable
fun AbilityChip(name: String) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(text = name, color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.bodySmall)
    }
}