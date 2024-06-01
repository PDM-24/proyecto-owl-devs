package com.owldevs.taskme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.model.ChatPreviewData

@Composable
fun ChatPreview(navController: NavController, chatData: ChatPreviewData) {
    val navy = colorResource(id = R.color.navy)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .background(color = navy)
            .clickable {
                navController.navigate("chat_screen/${chatData.userId}")
            }, // Manejador de clics para navegar a la pantalla de chat
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Gray, CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pfp),
                contentDescription = "Profile picture container",
                modifier = Modifier.size(60.dp),
                tint = Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = chatData.name, fontSize = 16.sp, color = Color.White, maxLines = 1)
            Text(text = chatData.message, fontSize = 14.sp, color = Color.Gray, maxLines = 1)
        }

        Text(text = chatData.time, fontSize = 12.sp, color = Color.Gray)
    }
}

