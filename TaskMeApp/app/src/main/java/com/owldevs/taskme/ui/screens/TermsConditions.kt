package com.owldevs.taskme.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.owldevs.taskme.R

@Composable
fun TermsConditions(
    navController: NavController
   // terms: List<String>
) {
    val terms = listOf("Lorem ipsum dolor sit amet.",
        "Consectetur adipiscing elit.",
        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "Lorem ipsum dolor sit amet.",
        "Consectetur adipiscing elit.",
        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")


    Box() {
        Row {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Icon(
                painter = painterResource(id = R.drawable.ic_taskme),
                contentDescription = "Emoji",
                tint = Color.Unspecified,
                modifier = Modifier.size(200.dp)
            )

            Text(
                text = "Forma parte de la familia",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(50.dp))



            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .padding(10.dp),
            ) {
                Text(
                    text = "Términos y condiciones",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    items(terms.size) { index ->
                        TermsAndConditionsItem(terms[index])
                    }
                }

            }



        }

    }
}

@Composable
fun TermsAndConditionsItem(term: String) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "\u2022 ", // Bullet point character
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.widthIn(20.dp)
        )
        Text(
            text = term,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(
    showSystemUi = true)
@Composable
fun DefaultPreview2() {
    /*val sampleTerms = listOf(
        "Lorem ipsum dolor sit amet.",
        "Consectetur adipiscing elit.",
        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "Lorem ipsum dolor sit amet.",
        "Consectetur adipiscing elit.",
        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )*/

    //TermsConditions(/*terms = sampleTerms*/)
}