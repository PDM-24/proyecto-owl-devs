package com.owldevs.taskme.ui.screens

import UserViewModel
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.components.NewTaskSection
import com.owldevs.taskme.ui.components.OrderDetailBtn
import com.owldevs.taskme.ui.viewmodels.ChatViewModel

@Composable
fun ChatScreen(navController: NavController, chatViewModel: ChatViewModel,  userViewModel: UserViewModel) {
    ProvideWindowInsets {
        val chatMessages by chatViewModel.messages.collectAsState(initial = emptyList())
        val currentUser by userViewModel.currentUser.observeAsState()
        val role = currentUser?.role
        val mobileNumber = currentUser?.phoneNo

        var messageText by remember { mutableStateOf("") }
        val context = LocalContext.current

        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .navigationBarsPadding()
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .imePadding()) {

                // Back button
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onBackground)
                }

                // Order details button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.tertiary)
                        .padding(vertical = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column{
                        if(role == "client"){
                            OrderDetailBtn(navController)
                        }else{
                            NewTaskSection(navController)
                        }

                        // Debugging: Show current user role
                        Text(
                            text = "Role: ${currentUser?.role ?: "No role"}",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }

                // Chat messages
                Box(modifier = Modifier.weight(1f)) {
                    var lastDisplayedDate: String? by remember { mutableStateOf(null) }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(chatMessages) { message ->
                            val messageDate = message.timestamp.split(" A LAS ")[0]
                            val messageTime = message.timestamp.split(" A LAS ")[1]

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(vertical = 8.dp)
                            ) {
                                if (!message.isUser) {
                                    Box(
                                        modifier = Modifier
                                            .padding(6.dp)
                                            .fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_pfp),
                                                contentDescription = "Profile Picture",
                                                modifier = Modifier
                                                    .size(70.dp)
                                                    .clip(CircleShape),
                                                tint = Color.Unspecified
                                            )
                                            Text(
                                                text = message.sender,
                                                color = MaterialTheme.colorScheme.onBackground,
                                                style = MaterialTheme.typography.titleLarge
                                            )

                                            Spacer(modifier = Modifier.height(6.dp))

                                            Text(
                                                text = "Recuerda establecer fecha, hora y precio del servicio para poder agendar la tarea.",
                                                color = MaterialTheme.colorScheme.onBackground,
                                                style = MaterialTheme.typography.bodyMedium,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }

                                }
                            }

                            Row(modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center){

                                Button(
                                    onClick = {
                                        val message = "Hello, I would like to contact you regarding a task."
                                        mobileNumber?.let {
                                            onClickWhatsApp(
                                                context = context,
                                                mobileNumber = it,
                                                message = message
                                            )
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth().padding(horizontal = 10.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(text = "Contactar via WhatsApp",
                                        color = MaterialTheme.colorScheme.primaryContainer,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.padding(8.dp))
                                }

                            }

                        }
                    }
                }

            }
        }
    }
}

fun onClickWhatsApp(
    context: Context,
    mobileNumber: String,
    message: String
) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(
            "https://wa.me/${mobileNumber.removePrefix("+")}?text=${message.replace(" ", "%20")}"
        )
    )
    context.startActivity(intent)
}
