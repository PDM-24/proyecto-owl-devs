package com.owldevs.taskme.ui.components

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
import com.owldevs.taskme.ui.navigation.MainScreens
import com.owldevs.taskme.ui.screens.CategoryScreen
import com.owldevs.taskme.ui.theme.TaskMeTheme

@Composable
fun UserInfoCard(
    navController: NavController,
    userImg: Int = R.drawable.ic_pfp,
    userName: String = "Jhon Doe",
    tasksCompleted: Int = 0,
    userDescription: String = "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum",
    dateJoined: Long = 123456,
    userRatings: Double = 1.5,
    ratingsNumber: Int = 0,
    disponible: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight()
            .clickable {
                navController.navigate(MainScreens.UserProfile.route)
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
                        painter = painterResource(id = userImg),
                        contentDescription = "User Img",
                        modifier = Modifier.size(64.dp)
                    )
                    Text(text = userName, style = MaterialTheme.typography.titleMedium)
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
                Text(text = "$tasksCompleted", style = MaterialTheme.typography.bodyMedium)
            }
            Text(
                text = userDescription,
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
                    Text(text = "$dateJoined", style = MaterialTheme.typography.bodySmall)
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "$userRatings", style = MaterialTheme.typography.titleMedium)
                    Icon(
                        imageVector = Icons.Filled.Build,
                        contentDescription = "Likes",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(text = "($ratingsNumber)", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

