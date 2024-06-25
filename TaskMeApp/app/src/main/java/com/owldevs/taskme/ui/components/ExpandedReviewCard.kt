package com.owldevs.taskme.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.owldevs.taskme.R
import com.owldevs.taskme.data.api.ReviewResponseApi
import java.time.Instant
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExpandedReviewCard(
    userImg: Int = R.drawable.ic_pfp,
    review: ReviewResponseApi
) {
    val dateFormatted =
        Instant.ofEpochMilli(review.fecha.time).atZone(ZoneId.of("UTC"))
            .toLocalDate()

    var viewMore by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = review.autorId.nombre, style = MaterialTheme.typography.titleMedium)
                }
                Row() {
                    if (!viewMore) {
                        TextButton(
                            onClick = { viewMore = !viewMore },
                            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSecondary)
                        ) {
                            Text(text = "ver mas")
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = "Arrow down"
                            )
                        }
                    } else {
                        TextButton(
                            onClick = { viewMore = !viewMore },
                            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSecondary)
                        ) {
                            Text(text = "ver menos")
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "Arrow down"
                            )
                        }
                    }
                }
            }
            Text(
                text = review.texto,
                maxLines = if (viewMore) Int.MAX_VALUE else 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${dateFormatted.dayOfMonth}/${dateFormatted.month}/${dateFormatted.year}", style = MaterialTheme.typography.bodySmall)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "${review.calificacion}", style = MaterialTheme.typography.bodyMedium)
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = "Rating",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}