package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.model.ChatPreviewData
import com.owldevs.taskme.ui.components.ChatPreview

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
            .background(color = navy)
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
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 25.dp, top = 40.dp, bottom = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "(box de filtro)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier.padding(start = 25.dp, top = 10.dp, bottom = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Recientes",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 25.dp, bottom = 10.dp)
                    )
                }
            }
            Divider(color = Color.White, thickness = 1.dp)
            Box(modifier = Modifier.fillMaxWidth()) {
                LazyColumn {
                    items(chatList) { chatData ->
                        ChatPreview(navController = navController, chatData = chatData)
                        Divider(color = Color.White, thickness = 1.dp)
                    }
                }
            }
        }
    }
}
