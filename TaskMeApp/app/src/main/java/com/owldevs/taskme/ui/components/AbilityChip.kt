package com.owldevs.taskme.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.owldevs.taskme.ui.theme.TaskMeTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close

@Composable
fun AbilityChip(
    abilityName: String = "Carpintero",
    showCloseIcon: Boolean = false,
    onRemove: () -> Unit = {}
) {
    AssistChip(
        onClick = { /* Handle chip click if needed */ },
        label = {
            Text(
                text = abilityName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        },
        trailingIcon = {
            if (showCloseIcon) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Remove",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { onRemove() }
                )
            }
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            labelColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}
