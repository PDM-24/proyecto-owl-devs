package com.owldevs.taskme.ui.screens

import UserViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.owldevs.taskme.R
import com.owldevs.taskme.ui.theme.TaskMeTheme

@Composable
fun UserHome(
    navController: NavController,
    userViewModel: UserViewModel = viewModel()
) {

    val navy = colorResource(id = R.color.navy)
    val cyan = colorResource(id = R.color.cyan)
    val currentUserState = userViewModel.currentUser.observeAsState()
    val currentUser = currentUserState.value
    val name = currentUser?.name

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = navy)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_homebg), // Replace with your image resource ID
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds // Scale the image to fill the bounds of the Box
        )
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(0.3f)
                    .background(color = Color.Gray.copy(alpha = 0.5f))
                    .padding(bottom = 40.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Task Me!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Conectando habilidades, cumpliendo tareas",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = name ?: "No name",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Transparent
                )
            }

            Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(0.2f)
                        .background(color = Color.Transparent),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "¿Qué necesitas?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "(box de filtro)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(1f)
                        .background(color = Color.Transparent),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Debugging: Show current user role
                    Text(
                        text = "Role: ${currentUser?.role ?: "No role"}",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                // to do
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(cyan)
                        ) {
                            Row(
                                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_carpentery),
                                    contentDescription = "Saw vector",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.width(7.dp))
                                Text(
                                    text = "Carpintería",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = navy
                                )
                            }

                        }

                        Button(
                            onClick = {
                                //to do
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(cyan)
                        ) {
                            Row(
                                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_taxi),
                                    contentDescription = "Taxi vector",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(40.dp)
                                )

                                Spacer(modifier = Modifier.width(7.dp))

                                Text(
                                    text = "Motorista",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = navy
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}