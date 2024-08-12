package com.example.lesson7.ui.profile_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.lesson7.ui.profile_screen.components.ProfileScreenContent

@Composable
fun ProfileScreen(
    navController: NavController,
    firstName: String,
    lastName: String,
    patronymic: String,
    birthday: String
) {
    ProfileScreenContent(
        onBackClick = navController::popBackStack,
        navController = navController,
        firstName = firstName,
        lastName = lastName,
        patronymic = patronymic,
        birthday = birthday
    )
}