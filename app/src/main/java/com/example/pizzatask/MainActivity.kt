package com.example.pizzatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pizzatask.mainScreen.PizzaDetailsScreen
import com.example.pizzatask.ui.theme.PizzaTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizzaTaskTheme {
                PizzaDetailsScreen()
            }
        }
    }
}
