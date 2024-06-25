package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.data.api.ApiTaskUserSuccessful
import com.owldevs.taskme.data.userTaskList
import com.owldevs.taskme.ui.components.TaskCard
import com.owldevs.taskme.ui.navigation.MyBottomNav
import com.owldevs.taskme.ui.theme.TaskMeTheme
import com.owldevs.taskme.ui.viewmodels.TaskApiViewModel
import com.owldevs.taskme.ui.viewmodels.UserApiViewModel

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    navController: NavController,
    taskApiViewModel: TaskApiViewModel = viewModel(),
    isTasker: Boolean = true
) {

    var filterSearch by remember { mutableStateOf("") }

   LaunchedEffect(Unit){
       taskApiViewModel.getUserTasks()
   }

    val tasks by taskApiViewModel.filteredTasks(filterSearch).collectAsState(initial = emptyList())

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
                    selected = filterSearch == "Pendiente",
                    onClick = {  filterSearch = if (filterSearch == "Pendiente") "" else "Pendiente"  },
                    label = {
                        Text(
                            text = "Pendiente",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
                FilterChip(
                    selected = filterSearch == "En Progreso",
                    onClick = {  filterSearch = if (filterSearch == "En Progreso") "" else "En Progreso"  },
                    label = {
                        Text(
                            text = "En Progreso",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
                FilterChip(
                    selected = filterSearch == "Completada",
                    onClick = { filterSearch = if (filterSearch == "Completada") "" else "Completada"  },
                    label = {
                        Text(
                            text = "Completada",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
                FilterChip(
                    selected = filterSearch == "Cancelados",
                    onClick = { filterSearch = if (filterSearch == "Cancelados") "" else "Cancelados"  },
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
                if(tasks.isNotEmpty()){
                    items(tasks) { task ->
                        TaskCard(navController, task)
                    }
                }else{
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No hay Task por el momento",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        }
    }

}
