package com.owldevs.taskme.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.data.api.ApiUserByCategorySuccessful
import com.owldevs.taskme.data.currentTasker
import com.owldevs.taskme.data.taskerId
import com.owldevs.taskme.ui.navigation.MainScreens
import com.owldevs.taskme.ui.navigation.SecondaryScreens
import com.owldevs.taskme.ui.screens.CategoryScreen
import com.owldevs.taskme.ui.theme.TaskMeTheme
import java.time.Instant
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserInfoCard(
    navController: NavController,
    user: ApiUserByCategorySuccessful,
    disponible: Boolean = false
) {
    val dateFormatted = Instant.ofEpochMilli(user.perfilTasker.fechaUnion.time).atZone(ZoneId.of("UTC")).toLocalDate()

    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight()
            .clickable {
                navController.navigate(SecondaryScreens.TaskerInfoScreen.route)
                taskerId.value = user.id
                currentTasker = currentTasker.copy(
                    id = user.id,
                    correoElectronico = user.correoElectronico,
                    nombre = user.nombre,
                    ubicacion = user.ubicacion,
                    usuarioTasker = user.usuarioTasker,
                    tarjetasAsociadas = user.tarjetasAsociadas,
                    perfilTasker = user.perfilTasker
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_pfp),
                        contentDescription = "User Img",
                        modifier = Modifier.size(64.dp)
                    )
                    Text(text = user.nombre, style = MaterialTheme.typography.titleMedium)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (disponible) {
                        Text(
                            text = "Disponible",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Box(
                            modifier = Modifier
                                .size(15.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.tertiary,
                                    shape = CircleShape
                                )
                        )
                    } else {
                        Text(
                            text = "No Disponible",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Box(
                            modifier = Modifier
                                .size(15.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.error,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Trabajos realizados: ", style = MaterialTheme.typography.titleMedium)
                Text(text = "${user.perfilTasker.trabajosRealizados}", style = MaterialTheme.typography.bodyMedium)
            }
            Text(
                text = user.perfilTasker.descripcionPersonal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Miembro desde: ",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "${dateFormatted.dayOfMonth}/${dateFormatted.month}/${dateFormatted.year}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "${user.perfilTasker.promedioCalificaciones}", style = MaterialTheme.typography.titleMedium)
                    Icon(
                        imageVector = Icons.Filled.Build,
                        contentDescription = "Likes",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(text = "(${user.perfilTasker.trabajosRealizados})", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

