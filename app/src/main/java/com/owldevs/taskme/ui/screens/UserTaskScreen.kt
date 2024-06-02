package com.owldevs.taskme.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.owldevs.taskme.R


@Composable
fun UserTaskScreen(navController: NavController){
    val navy = colorResource(id = R.color.navy);


    Box(modifier = Modifier
        .background(navy)
        .fillMaxSize()
        ){
        Column(modifier = Modifier
            .fillMaxSize(),
        ) {
            // Back button
            IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.padding(10.dp)) {
                Image(painter = painterResource(id = R.drawable.ic_backbtn), contentDescription = "backButton", modifier = Modifier.size(25.dp))
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                ,horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(painter = painterResource(id = R.drawable.pfp_cont), contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Column {
                    Text(text = "Jhon Doe", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 15.sp)
                    Text(text = "En proceso", color = Color.White, fontSize = 10.sp, modifier = Modifier.padding(top = 0.dp, bottom = 5.dp, start = 0.dp, end = 0.dp))
                    Text(text = "03/04/2024,"+" 3:00 a.m.", color = Color.White,modifier = Modifier.padding(top = 0.dp, bottom = 5.dp, start = 0.dp, end = 0.dp))
                    Text(text = "Ver perfil", modifier = Modifier.clickable {  },textDecoration = TextDecoration.Underline, color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                item {
                        OrderDetails()
                }
                item {
                    PaymentMethod()
                }
                item {
                    DeliveryDetail()
                }
                item {
                    FinalizeButton()
                }
                item {
                    CancelButton()
                }
            }
        }
    }
}

@Composable
fun OrderDetails(){
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Tu pedido", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 15.dp))

            IconButton(onClick = { expanded = !expanded }) {
               Image(
                   painter =
                   if (expanded) painterResource(id = R.drawable.arrowdropdown_btn) else painterResource(id = R.drawable.arrowdropdownleft_btn),
                   contentDescription = if (expanded) "arrow down" else "arrow down"
               )
            }
        }
        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Row (modifier = Modifier
                    .fillMaxWidth()
                ){
                    Image(painter = painterResource(id = R.drawable.imagetask), contentDescription = "task image", modifier = Modifier.size(100.dp))
                    Spacer(modifier = Modifier.width(15.dp))
                    Column (
                        modifier = Modifier.padding(top = 20.dp)
                    ){
                        Text(text = "ID", color = Color.White)
                        Text(text = "Fontaneria", color = Color.White)
                        Text(text = "$20.00", color = Color.White)
                    }
                }
                Text(text = "Tu pago:", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
                Spacer(modifier = Modifier.height(2.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "productos", color = Color.White)
                    Text(text = "US"+"$"+"0.00", color = Color.White)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "tarifa de servicio", color = Color.White)
                    Text(text = "US"+"$"+"0.00", color = Color.White)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "Total:", color = Color.White,fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "US"+"$"+"0.00", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun PaymentMethod(){
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Medios de pago", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 15.dp))

            IconButton(onClick = { expanded = !expanded }) {
                Image(
                    painter =
                    if (expanded) painterResource(id = R.drawable.arrowdropdown_btn) else painterResource(id = R.drawable.arrowdropdownleft_btn),
                    contentDescription = if (expanded) "arrow down" else "arrow down"
                )
            }
        }
        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Image(painter = painterResource(id = R.drawable.moneyicon), contentDescription = "payment Method")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Efectivo", color = Color.White)
                    }
                    Text(text = "US"+"$"+"0.00", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun DeliveryDetail(){
    var expanded by remember { mutableStateOf(false) }
    Column (modifier = Modifier.padding(8.dp)){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Detalles sobre la entrega", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 15.dp))

            IconButton(onClick = { expanded = !expanded }) {
                Image(
                    painter =
                    if (expanded) painterResource(id = R.drawable.arrowdropdown_btn) else painterResource(id = R.drawable.arrowdropdownleft_btn),
                    contentDescription = if (expanded) "arrow down" else "arrow down"
                )
            }
        }
        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Image(painter = painterResource(id = R.drawable.locationicon), contentDescription = "payment Method")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Direccion", color = Color.White)
                }
            }
            
        }
        
        
    }
}

@Composable
fun FinalizeButton(){
    val cyan = colorResource(id = R.color.cyan)
    val navy = colorResource(id = R.color.navy)
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = { /*TODO*/ }
            , colors = ButtonDefaults.buttonColors(
                containerColor = cyan
            ), modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 50.dp, end = 50.dp, top = 10.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Marcar como finalizado", color = navy)
        }
    }
}
@Composable
fun CancelButton(){
    val salmon = colorResource(id = R.color.salmon)
    val cyan = colorResource(id = R.color.cyan)
    val navy = colorResource(id = R.color.navy)
    val mustard = colorResource(id = R.color.mustard)
    var showDialog by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = { showDialog = true }
            , colors = ButtonDefaults.buttonColors(
                containerColor = salmon
            ), modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(start = 50.dp, end = 50.dp, top = 10.dp, bottom = 10.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Cancelar pedido", color = Color.White)
        }


        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                text = { Text("¿Deseas cancelar tu pedido?", color = navy, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, fontSize = 20.sp   ) },
                containerColor = mustard,
                properties = DialogProperties(dismissOnClickOutside = false),
                confirmButton = {
                    Button(onClick = { showDialog = false }
                        , colors = ButtonDefaults.buttonColors(
                            containerColor = salmon
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Sí, cancelar pedido", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }
                        , colors = ButtonDefaults.buttonColors(
                            containerColor = cyan
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("No cancelar", color = navy)
                    }
                }
            )
        }
    }
    }
