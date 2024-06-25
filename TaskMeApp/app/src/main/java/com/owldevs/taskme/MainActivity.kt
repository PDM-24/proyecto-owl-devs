package com.owldevs.taskme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.owldevs.taskme.ui.navigation.MyAppNavigation
import com.owldevs.taskme.ui.theme.TaskMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskMeTheme {
                Surface() {
                    MyAppNavigation()
                }
            }
        }
    }
}

