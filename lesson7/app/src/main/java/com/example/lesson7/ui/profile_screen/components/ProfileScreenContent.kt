package com.example.lesson7.ui.profile_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.lesson7.ui.navigation.navigateToEditProfileScreen
import com.example.lesson7.ui.theme.LocalCustomColors

@Composable
fun ProfileScreenContent(
    onBackClick: () -> Unit,
    navController: NavController,
    firstName: String,
    lastName: String,
    patronymic: String,
    birthday: String
) {
    val firstNameState = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>(UserKeys.FIRST_NAME_KEY)?.observeAsState()
    val lastNameState = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>(UserKeys.LAST_NAME_KEY)?.observeAsState()
    val patronymicState = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>(UserKeys.PATRONYMIC_KEY)?.observeAsState()
    val birthdayState = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>(UserKeys.BIRTHDAY_KEY)?.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {

        CustomToolBar(
            text = stringResource(id = R.string.title_profile),
            color = LocalCustomColors.current.GrayBG,
            onBackClick = onBackClick
        )
        ProfileCard(
            firstName = firstNameState?.value ?: firstName,
            lastName = lastNameState?.value ?: lastName,
            patronymic = patronymicState?.value ?: patronymic,
            birthday = birthdayState?.value ?: birthday
        )
        ProfileInfo()
        Box(modifier = Modifier.weight(1f))
        CustomButton(
            text = stringResource(id = R.string.button_edit_profile),
            onClick = {
                navController.navigateToEditProfileScreen(
                    firstName = firstNameState?.value ?: firstName,
                    lastName = lastNameState?.value ?: lastName,
                    patronymic = patronymicState?.value ?: patronymic,
                    birthday = birthdayState?.value ?: birthday,
                )
            }
        )
    }
}

@Preview
@Composable
fun ProfileScreenContentPreview() {
    ProfileScreenContent(
        {},
        rememberNavController(),
        "Иван",
        "Иванов",
        "Иванович",
        "01.01.2001"
    )
}