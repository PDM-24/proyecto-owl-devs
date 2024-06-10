package com.owldevs.taskme.ui.components

import android.content.res.Configuration
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.owldevs.taskme.ui.theme.TaskMeTheme

@Composable
fun AbilityChip(
    abilityName: String = "Carpintero"
) {
    AssistChip(
        onClick = { /*TODO*/ },
        label = {
            Text(
                text = abilityName,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            labelColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}

