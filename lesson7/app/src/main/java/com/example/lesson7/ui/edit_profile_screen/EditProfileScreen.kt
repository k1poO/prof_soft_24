package com.example.lesson7.ui.edit_profile_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.lesson7.ui.edit_profile_screen.components.EditProfileScreenContent

@Composable
fun EditProfileScreen(
    navController: NavController,
    firstName: String,
    lastName: String,
    patronymic: String,
    birthday: String
) {
    EditProfileScreenContent(
        onBackClick = navController::popBackStack,
        navController = navController,
        firstName = firstName,
        lastName = lastName,
        patronymic = patronymic,
        birthday = birthday
    )
}