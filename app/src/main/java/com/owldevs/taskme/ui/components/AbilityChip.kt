package com.owldevs.taskme.ui.components

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AbilityChip(
    abilityName: String = "Carpintero"
) {
    AssistChip(
        onClick = { /*TODO*/ },
        label = { Text(text = abilityName, style = MaterialTheme.typography.bodyLarge) },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            labelColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}

