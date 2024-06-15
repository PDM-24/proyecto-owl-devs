package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.R
import com.owldevs.taskme.model.ChatPreviewData
import com.owldevs.taskme.ui.components.ChatPreview
import com.owldevs.taskme.ui.theme.TaskMeTheme

@Composable
fun UserMailbox(navController: NavController) {
    val navy = colorResource(id = R.color.navy)
    val chatList = listOf(
        ChatPreviewData("1", "John Doe", "Hola! Me gustaría que...", "00:00 a.m"),
        ChatPreviewData("2", "John Doe1", "Hola! Me gustaría que...", "00:00 a.m"),
        ChatPreviewData("3", "John Doe2", "Hola! Me gustaría que...", "00:00 a.m")
        // Agrega más datos de ejemplo según sea necesario
    )

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Bandeja de entrada",
                        style= MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(start = 25.dp, top = 40.dp, bottom = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "(box de filtro)",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(start = 25.dp, top = 10.dp, bottom = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Recientes",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(start = 25.dp, bottom = 10.dp)
                    )
                }
            }
            Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 1.dp)
            Box(modifier = Modifier.fillMaxWidth()) {
                LazyColumn {
                    items(chatList) { chatData ->
                        ChatPreview(navController, chatData)
                        Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 1.dp)
                    }
                }
            }
        }
    }
}