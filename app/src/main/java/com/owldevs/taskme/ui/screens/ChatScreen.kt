package com.owldevs.taskme.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.viewmodels.ChatViewModel
import com.owldevs.taskme.ui.theme.*


@Composable
fun ChatScreen(navController: NavController, chatViewModel: ChatViewModel, userId: String) {
    val chatMessages by chatViewModel.messages.collectAsState(initial = emptyList())
    val navy = colorResource(id = R.color.navy)
    var messageText by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .background(color = navy)
        .fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Back button
            IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.padding(16.dp)) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }

            // Order details button
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(color = NaranjaIntenso)
                .padding(vertical = 20.dp)
            ) {
                Button(
                    onClick = { /* Handle order details click */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Turquesa),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_detail),
                            contentDescription = "order detail",
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column{
                            Text(text = "Detalles de la tarea", color = AzulMarino, fontSize = 18.sp)
                            Text(text = "Conoce el estado de tu pedido", color = AzulMarino)
                        }
                    }


                }
            }

            // Chat messages
            Box(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(chatMessages) { message ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            if (!message.isUser) {
                                Image(
                                    painter = painterResource(id = R.drawable.pfp_cont),
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .align(Alignment.CenterHorizontally)
                                )
                                Text(
                                    text = message.sender,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                            Text(
                                text = message.text,
                                color = if (message.isUser) Color.White else Color.Black,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .background(
                                        if (message.isUser) Color(0xFF00C2FF) else Color.White,
                                        RectangleShape
                                    )
                                    .padding(8.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = message.timestamp,
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Message input row

                Row (verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(10.dp)
                    ){


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(5.dp))
                            .padding(top = 2.dp, bottom = 2.dp)
                            .fillMaxWidth(0.75f)
                    ) {
                        IconButton(onClick = { /* Emoji icon click */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_smileyy),
                                contentDescription = "Emoji",
                                tint = Color.Unspecified
                            )
                        }
                        BasicTextField(
                            value = messageText,
                            onValueChange = { messageText = it },
                            modifier = Modifier.weight(1f)
                        )

                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(1.dp)
                    ) {
                        IconButton(onClick = { /* Camera icon click */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = "Camera",
                                tint = Color.Unspecified
                            )
                        }
                        IconButton(onClick = {
                            if (messageText.isNotEmpty()) {
                                chatViewModel.sendMessage(messageText)
                                messageText = ""
                            }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_send),
                                contentDescription = "Send",
                                tint = Color.Unspecified
                            )
                        }
                    }

            }
        }
    }
}


