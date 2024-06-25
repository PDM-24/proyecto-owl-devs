package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.owldevs.taskme.ui.components.ChatPreview
import com.owldevs.taskme.ui.viewmodels.UserApiViewModel

@Composable
fun UserMailbox(navController: NavController, userApiViewModel: UserApiViewModel = viewModel()) {

    val chatList by userApiViewModel.mailbox.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        userApiViewModel.getChatPreviewsByUser(userApiViewModel.currentUser.value?.id ?: "")
    }

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Bandeja de entrada",
                        style= MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(start = 25.dp, top = 40.dp, bottom = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Recientes",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(start = 25.dp, bottom = 10.dp)
                    )
                }
            }
            Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 1.dp)
            Box(modifier = Modifier.fillMaxWidth()) {
                LazyColumn {
                    items(chatList) { chatData ->
                        ChatPreview(navController, chatData)
                        Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 1.dp)
                    }
                }
            }
        }
    }
}
