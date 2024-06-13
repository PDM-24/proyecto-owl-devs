package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.components.UserInfoCard
import com.owldevs.taskme.ui.navigation.MyBottomNav
import com.owldevs.taskme.ui.theme.TaskMeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    categoryName: String = "CategoryName",
    taskerDirection: String = "San Salvador, El Salvador"
) {
    var userSearch by remember { mutableStateOf("") }

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
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.zIndex(1f)
            )
        }
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
                color = MaterialTheme.colorScheme.onBackground
            )
        }
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
        Text(
            text = "Taskers destacados en tu area: ",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
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
            if (true) {
                items(count = 5) {
                    UserInfoCard()
                }
            } else {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay Taskers en esta ubicacion",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
    }
}
