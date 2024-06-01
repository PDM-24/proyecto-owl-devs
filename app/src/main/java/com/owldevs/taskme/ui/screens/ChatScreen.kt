package com.owldevs.taskme.ui.screens

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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.viewmodels.ChatViewModel
import com.owldevs.taskme.ui.theme.*

@Composable
fun ChatScreen(navController: NavController, chatViewModel: ChatViewModel, userId: String) {
    ProvideWindowInsets {
        val chatMessages by chatViewModel.messages.collectAsState(initial = emptyList())

        val navy = colorResource(id = R.color.navy)
        val latoBold = FontFamily(Font(R.font.lato_bold))
        val nunitoRegular = FontFamily(Font(R.font.nunito_regular))

        var messageText by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .background(color = navy)
                .fillMaxSize().navigationBarsPadding()
        ) {
            Column(modifier = Modifier.fillMaxSize()
                .imePadding()) {
                // Back button
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }

                // Order details button
                Box(
                    modifier = Modifier
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_detail),
                                contentDescription = "order detail",
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Detalles de la tarea",
                                    color = AzulMarino,
                                    fontSize = 18.sp,
                                    fontFamily = latoBold
                                )
                                Text(
                                    text = "Conoce el estado de tu pedido",
                                    color = AzulMarino,
                                    fontSize = 16.sp,
                                    fontFamily = nunitoRegular
                                )
                            }
                        }
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
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 24.sp
                                            )

                                            Spacer(modifier = Modifier.height(6.dp))

                                            Text(
                                                text = "Recuerda establecer fecha, hora y precio del servicio para poder agendar la tarea.",
                                                color = Color.White,
                                                fontSize = 16.sp,
                                                textAlign = TextAlign.Center,
                                                fontFamily = nunitoRegular
                                            )
                                        }
                                    }

                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                if (messageDate != lastDisplayedDate) {
                                    lastDisplayedDate = messageDate
                                    Text(
                                        text = messageDate,
                                        color = Color.Gray,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                            Row(
                                horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            ) {
                                if (!message.isUser) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_pfp),
                                        contentDescription = "Profile Picture",
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(CircleShape)
                                            .align(alignment = Alignment.Bottom),
                                        tint = Color.Unspecified
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                }

                                Column(
                                    horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start,
                                    modifier = Modifier
                                        .background(
                                            if (message.isUser) Color(0xFF007193) else Color.White,
                                            RoundedCornerShape(10.dp)
                                        )
                                        .padding(horizontal = 10.dp, vertical = 8.dp)
                                        .widthIn(max = (LocalConfiguration.current.screenWidthDp.dp * 0.6f))
                                ) {

                                    Text(
                                        text = message.text,
                                        color = if (message.isUser) Color.White else Color.Black,
                                        fontSize = 14.sp,
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = messageTime,
                                        color = if (message.isUser) Color.White else Color.Gray,
                                        fontSize = 12.sp
                                    )
                                }

                                if (message.isUser) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_pfp),
                                        contentDescription = "User Profile Picture",
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(CircleShape)
                                            .align(alignment = Alignment.Bottom),
                                        tint = Color(0xFF007193)
                                    )
                                }
                            }
                        }
                    }
                }

                // Message input row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(start= 10.dp, end= 10.dp, top= 2.dp, bottom= 14.dp).navigationBarsWithImePadding()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(5.dp))
                            .padding(vertical = 4.dp)
                            .fillMaxWidth(0.75f)
                    ) {
                        BasicTextField(
                            value = messageText,
                            onValueChange = { messageText = it },
                            modifier = Modifier.weight(1f).padding(vertical= 6.dp, horizontal = 12.dp)
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
}




