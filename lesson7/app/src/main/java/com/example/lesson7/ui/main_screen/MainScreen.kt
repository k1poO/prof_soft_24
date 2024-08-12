package com.example.lesson7.ui.main_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.lesson7.ui.main_screen.components.MainScreenContent
import com.example.lesson7.ui.navigation.navigateToProfileScreen

@Composable
fun MainScreen(navController: NavController) {
    MainScreenContent {
        navController.navigateToProfileScreen(
            firstName = "Kirill",
            lastName = "Putyatin",
            patronymic = "Sergeevich",
            birthday = "14.04.2002"
        )
    }
}