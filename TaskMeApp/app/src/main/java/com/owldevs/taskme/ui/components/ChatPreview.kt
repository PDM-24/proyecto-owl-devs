package com.owldevs.taskme.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.model.ChatPreviewData
import com.owldevs.taskme.ui.navigation.SecondaryScreens

@Composable
fun ChatPreview(navController: NavController, chatData: ChatPreviewData) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .clickable {
                navController.navigate(SecondaryScreens.ChatScreen.route)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = MaterialTheme.colorScheme.background, CircleShape)
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
            modifier = Modifier.weight(1f).background(color = MaterialTheme.colorScheme.background)
        ) {
            Text(text = chatData.name, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground, maxLines = 1)
            Text(text = chatData.message, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground, maxLines = 1)
        }

        Text(text = chatData.time, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground)
    }
}

