package com.owldevs.taskme.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.data.api.ApiClient
import com.owldevs.taskme.data.api.ApiService
import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.data.api.Habilidad
import com.owldevs.taskme.data.api.UserApi
import com.owldevs.taskme.model.HacerTaskerRequest
import com.owldevs.taskme.model.UpdateUserRequest
import com.owldevs.taskme.ui.components.AbilityChip
import com.owldevs.taskme.ui.navigation.MainScreens
import com.owldevs.taskme.ui.navigation.SecondaryScreens
import com.owldevs.taskme.ui.viewmodels.CategoryViewModel
import com.owldevs.taskme.ui.viewmodels.UserApiViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun UsertoTaskerScreen(
    navController: NavController,
    userApiViewModel: UserApiViewModel = viewModel(),
    categoryViewModel: CategoryViewModel = viewModel()
) {


    val currentUser by userApiViewModel.currentUser.observeAsState()
    val selectedCategorias = remember { mutableStateListOf<String>() }
    val categories by categoryViewModel.categories.observeAsState(emptyList())
    LaunchedEffect(Unit) {
        categoryViewModel.fetchCategories()
    }



    val cyan = colorResource(id = R.color.cyan)
    var num_tel by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var habilidades by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var agreedToTerms by remember { mutableStateOf(false) }

    Box {
        Row {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Icon(
                    painter = painterResource(id = R.drawable.ic_taskme),
                    contentDescription = "Emoji",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(200.dp)
                )

                Text(
                    text = "Forma parte de la familia",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(50.dp))

                Column {
                    Text(
                        text = "Telefono",
                        style = MaterialTheme.typography.titleMedium
                    )
                    TextField(
                        value = num_tel,
                        onValueChange = { num_tel = it },
                        modifier = Modifier.width(300.dp),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )

                    Text(
                        text = "Acerca de ti",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = description,
                        onValueChange = { description = it },
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

                Spacer(modifier = Modifier.height(20.dp))

                Column {
                    Text(
                        text = "Habilidades",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        TextField(
                            value = habilidades,
                            onValueChange = { },
                            readOnly = true,
                            textStyle = MaterialTheme.typography.bodyMedium,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .width(300.dp)
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            categories.forEach { categoria ->
                                DropdownMenuItem(
                                    text = { Text(categoria.nombre) },
                                    onClick = {
                                        if (categoria.nombre !in selectedCategorias) {
                                            selectedCategorias.add(categoria.nombre)
                                        }
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentWidth()
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.Center,
                        maxItemsInEachRow = 4,
                    ) {
                        selectedCategorias.forEach { categoria ->
                            AbilityChip(
                                abilityName = categoria,
                                showCloseIcon = true,
                                onRemove = { selectedCategorias.remove(categoria) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.width(30.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(300.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = agreedToTerms,
                            onCheckedChange = { agreedToTerms = it },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        ClickableText(
                            text = AnnotatedString.Builder().apply {
                                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSecondary)) {
                                    append("He leído y acepto los ")
                                }
                                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                    append("términos y condiciones")
                                }
                            }.toAnnotatedString(),
                            onClick = {
                                navController.navigate(SecondaryScreens.TermsConditions.route)
                            },
                            modifier = Modifier.padding(start = 8.dp),
                        )
                    }
                }

                Spacer(modifier = Modifier.width(30.dp))

                Button(
                    onClick = {

                            val habilidadesList = selectedCategorias.map { categoriaNombre ->
                                Habilidad(nombre = categoriaNombre)
                            }
                            val updateRequest = HacerTaskerRequest(
                                id = currentUser?.id,
                                telefono = num_tel,
                                usuarioTasker = true,
                                descripcion_personal = description,
                                habilidades = habilidadesList
                            )
                            Log.d("Request Data", "id: ${currentUser?.id}, telefono: $num_tel, descripcion: $description, habilidades: $habilidadesList")
                            userApiViewModel.hacermeTasker(updateRequest)
                            navController.navigate(MainScreens.TaskerProfile.route)
                        }
                    ,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(cyan),
                    enabled = agreedToTerms // Enable button based on checkbox
                ) {
                    Text(text = "Convertirme en tasker")
                }


                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

