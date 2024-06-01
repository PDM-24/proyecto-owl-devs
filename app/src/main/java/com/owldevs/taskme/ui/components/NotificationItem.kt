package com.owldevs.taskme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.owldevs.taskme.R

@Composable
fun NotificationItem(
    notificationImg: Int = R.drawable.pfp_cont,
    userName: String = "Jhon Doe",
    notificationBody: String = "lorem ipsum lorem ipsum",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.secondary)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.onPrimary)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = notificationImg),
            contentDescription = "Notification Img"
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = userName,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = notificationBody,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Box(
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.error,
                shape = CircleShape
            )
        ) {
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(24.dp)) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Delete Notification",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}