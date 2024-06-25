package com.owldevs.taskme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.data.ApiUserBySuccessful
import com.owldevs.taskme.data.api.ApiTaskUserSuccessful
import com.owldevs.taskme.data.categoria
import com.owldevs.taskme.data.estado
import com.owldevs.taskme.data.metodo_pago
import com.owldevs.taskme.data.precio
import com.owldevs.taskme.data.taskId
import com.owldevs.taskme.data.taskernombre
import com.owldevs.taskme.data.ubicacion
import com.owldevs.taskme.ui.navigation.SecondaryScreens
import com.owldevs.taskme.ui.viewmodels.TaskApiViewModel
import com.owldevs.taskme.ui.viewmodels.UserApiViewModel
import java.time.Instant
import java.time.ZoneId

@Composable
fun TaskCard(
    navController: NavController,
    task: ApiTaskUserSuccessful
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Task N°: ", style = MaterialTheme.typography.bodyMedium)
                Text(text = task.id, style = MaterialTheme.typography.bodySmall)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Task Status",
                    modifier = Modifier.size(32.dp)
                )
                Text(text = task.estado, style = MaterialTheme.typography.bodyMedium)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "Task Category",
                    modifier = Modifier.size(32.dp)
                )
                Text(text = task.categoria.nombre, style = MaterialTheme.typography.bodyMedium)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_pfp),
                        contentDescription = "Tasker Img",
                        modifier = Modifier.size(32.dp)
                    )
                    Text(text = task.cliente_id.nombreCompleto, style = MaterialTheme.typography.bodyMedium)
                }
                Button(
                    onClick = {



                        navController.navigate(SecondaryScreens.UserTaskScreen.route)
                              },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                ) {
                    Text(
                        text = "Ver mas",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}