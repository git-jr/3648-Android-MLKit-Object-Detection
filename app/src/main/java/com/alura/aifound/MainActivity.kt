package com.alura.aifound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alura.aifound.ui.home.HomeScreen
import com.alura.aifound.ui.theme.AiFoundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AiFoundTheme {
                HomeScreen()
            }
        }
    }
}

