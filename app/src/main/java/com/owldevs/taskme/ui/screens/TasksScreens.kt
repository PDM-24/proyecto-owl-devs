package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.owldevs.taskme.ui.components.TaskCard
import com.owldevs.taskme.ui.navigation.MyBottomNav
import com.owldevs.taskme.ui.theme.TaskMeTheme

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    isTasker: Boolean = true
) {

    var filterSearch by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .padding()
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = if (isTasker) "Mis pedidos" else "Mis Trabajos",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Filtros: ",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                maxItemsInEachRow = 4
            ) {
                FilterChip(
                    selected = filterSearch == "Soliciados",
                    onClick = { filterSearch = "Soliciados" },
                    label = {
                        Text(
                            text = "Solicitados",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
                FilterChip(
                    selected = filterSearch == "Aceptados",
                    onClick = { filterSearch = "Aceptados" },
                    label = {
                        Text(
                            text = "Aceptados",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
                FilterChip(
                    selected = filterSearch == "Finalizados",
                    onClick = { filterSearch = "Finalizados" },
                    label = {
                        Text(
                            text = "Finalizados",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
                FilterChip(
                    selected = filterSearch == "Cancelados",
                    onClick = { filterSearch = "Cancelados" },
                    label = {
                        Text(
                            text = "Cancelados",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(10) {
                    TaskCard()
                }
            }
        }
    }

}