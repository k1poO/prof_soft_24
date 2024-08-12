package com.example.lesson7.ui.edit_profile_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lesson7.R
import com.example.lesson7.ui.components.CustomButton
import com.example.lesson7.ui.components.CustomToolBar
import com.example.lesson7.ui.navigation.UserKeys

@Composable
fun EditProfileScreenContent(
    onBackClick: () -> Unit,
    navController: NavController,
    firstName: String,
    lastName: String,
    patronymic: String,
    birthday: String,
) {
    val firstNameState = rememberSaveable { mutableStateOf(firstName) }
    val lastNameState = rememberSaveable { mutableStateOf(lastName) }
    val patronymicState = rememberSaveable { mutableStateOf(patronymic) }
    val birthdayState = rememberSaveable { mutableStateOf(birthday) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CustomToolBar(
            text = stringResource(id = R.string.title_edit_profile),
            color = Color.White,
            onBackClick = onBackClick
        )
        EditProfileInfoBlock(
            firstNameState = firstNameState,
            lastNameState = lastNameState,
            patronymicState = patronymicState,
            birthdayState = birthdayState
        )
        Box(modifier = Modifier.weight(1f))
        CustomButton(
            text = stringResource(id = R.string.button_save),
            onClick = {
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    UserKeys.FIRST_NAME_KEY,
                    firstNameState.value
                )
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    UserKeys.LAST_NAME_KEY,
                    lastNameState.value
                )
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    UserKeys.PATRONYMIC_KEY,
                    patronymicState.value
                )
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    UserKeys.BIRTHDAY_KEY,
                    birthdayState.value
                )
                navController.popBackStack()
            }
        )
    }
}

@Preview
@Composable
fun EditProfileScreenContentPreview() {
    EditProfileScreenContent(
        onBackClick = {},
        navController = rememberNavController(),
        firstName = "Иван",
        lastName = "Иванович",
        patronymic = "Иванов",
        birthday = "01.01.2001"
    )
}