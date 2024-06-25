package com.owldevs.taskme.ui.screens


import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.owldevs.taskme.R
import com.owldevs.taskme.data.api.DetallesPerfilTasker
import com.owldevs.taskme.data.api.Habilidad
import com.owldevs.taskme.model.UpdateUserRequest
import com.owldevs.taskme.ui.components.AbilityChip
import com.owldevs.taskme.ui.navigation.MainScreens
import com.owldevs.taskme.ui.viewmodels.CategoryViewModel
import com.owldevs.taskme.ui.viewmodels.UserApiViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun EditProfile(
    navController: NavController,
    userApiViewModel: UserApiViewModel = viewModel(),
    categoryViewModel: CategoryViewModel = viewModel()
) {




    val currentUser by userApiViewModel.currentUser.observeAsState()
    val initialUserName = currentUser?.nombre_completo ?: ""
    val initialEmail = currentUser?.correo_electronico ?: ""
    val isTasker = currentUser?.usuarioTasker ?: false
    val taskerProfile = currentUser?.perfilTasker

    val categories by categoryViewModel.categories.observeAsState(emptyList())



    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var newName by remember { mutableStateOf(initialUserName) }
    var newEmail by remember { mutableStateOf(initialEmail) }
    var descripcion by remember { mutableStateOf(taskerProfile?.descripcion_personal ?: "") }
    var expanded by remember { mutableStateOf(false) }
    var categorias by remember { mutableStateOf("") }
    val selectedCategorias = remember { mutableStateListOf<String>() }
    val scrollState = rememberLazyListState()
    val errorMessage by userApiViewModel::errorMessage
    val snackbarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }




    LaunchedEffect(Unit) {
        categoryViewModel.fetchCategories()
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar("Datos actualizados correctamente")
            showSnackbar = false
            navController.popBackStack()
            navController.navigate("editProfile")
        }
    }



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Editar perfil") },
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
        }
    ) { innerPadding ->
        LazyColumn(
            state = scrollState,
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (imageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = "Profile picture",
                            modifier = Modifier.size(60.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_pfp),
                            contentDescription = "Profile picture container",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { imagePickerLauncher.launch("image/*") },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                    ) {
                        Text(text = "Agregar o cambiar foto de perfil")
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }

            item {
                Column {
                    Text(
                        text = "Nombre",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        modifier = Modifier.width(300.dp), // Fixed width
                        value = newName,
                        onValueChange = { newName = it },
                        textStyle = MaterialTheme.typography.bodyMedium,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Column {
                    Text(
                        text = "Correo",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        modifier = Modifier.width(300.dp), // Fixed width
                        value = newEmail,
                        onValueChange = { newEmail = it },
                        textStyle = MaterialTheme.typography.bodyMedium,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                            unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        isError = !isValidEmail(newEmail), // Check if email is invalid
                        singleLine = true // Ensure it's a single line input
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
            }

            if (isTasker) {
                item {
                    Column(
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Text(
                            text = "Descripcion personal",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            value = descripcion,
                            onValueChange = { descripcion = it },
                            modifier = Modifier.width(300.dp), // Fixed width
                            textStyle = MaterialTheme.typography.bodyMedium,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                unfocusedContainerColor = MaterialTheme.colorScheme.onBackground,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }

                item {
                    Column {
                        Text(
                            text = "Categorias",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded }
                        ) {
                            TextField(
                                value = categorias,
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
                                    .width(300.dp), // Fixed width
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                categories.forEach { category ->
                                    DropdownMenuItem(
                                        text = { Text(category.nombre) },
                                        onClick = {
                                            if (category.nombre !in selectedCategorias) {
                                                selectedCategorias.add(category.nombre)
                                            }
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

                item {
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
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }

            item {
                Button(
                    onClick = {
                        val habilidadesList = selectedCategorias.map { categoriaNombre ->
                            Habilidad(nombre = categoriaNombre)
                        }
                        val updateRequest = UpdateUserRequest(
                            nombre_completo = newName,
                            correo_electronico = newEmail,
                            foto_perfil = imageUri?.toString(),
                            perfil_tasker = DetallesPerfilTasker(
                                descripcion_personal = descripcion,
                                habilidades = habilidadesList
                            )
                        )
                        userApiViewModel.updateProfile(updateRequest)
                        showSnackbar = true
                        navController.popBackStack()
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Guardar")
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return emailRegex.toRegex().matches(email)
}