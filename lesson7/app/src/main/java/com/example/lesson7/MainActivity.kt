package com.example.lesson7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.lesson7.ui.navigation.MAIN_SCREEN_ROUTE
import com.example.lesson7.ui.navigation.NavHost
import com.example.lesson7.ui.theme.Lesson7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lesson7Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = MAIN_SCREEN_ROUTE)
            }
        }
    }
}