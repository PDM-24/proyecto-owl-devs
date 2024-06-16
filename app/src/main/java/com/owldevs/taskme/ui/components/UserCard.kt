package com.owldevs.taskme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.owldevs.taskme.R

@Composable
fun UserCard(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit){
    val checkedColor = colorResource(id = R.color.salmon)
    val navy = colorResource(id = R.color.navy)
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
        , horizontalArrangement = Arrangement.Center
    ){
        Row (verticalAlignment = Alignment.CenterVertically){
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = checkedColor
                )
            )
            Box (
                modifier = Modifier
                    .width(300.dp)
                    .height(if (isChecked) 200.dp else 150.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            ){
                Column {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(13.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Row(
                            Modifier.padding(top = 10.dp)
                        ) {
                            Image(painter = painterResource(id = R.drawable.visaimage), contentDescription = "visa card")
                            Spacer(modifier = Modifier.width(15.dp))
                            Text(text = "**** 00000", color = navy)
                        }
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = "Eliminar", textDecoration = TextDecoration.Underline, color = navy)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(13.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Jon Doe", color = navy)
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "06/27", color = navy)
                    }

                    if(isChecked){
                        Box (
                            modifier = Modifier
                                .border(
                                    width = 2.dp,
                                    color = Color.DarkGray,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .width(260.dp)
                                .padding(horizontal = 10.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 3.dp)
                        ){
                            Column {
                                Text(text = "CVV/CVC:", color = Color.DarkGray)
                                Text(text = "3 o 4 digitos", color = Color.DarkGray)
                            }
                        }
                    }
                }

            }

        }
    }
}