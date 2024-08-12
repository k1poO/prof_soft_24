package com.example.lesson7.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lesson7.ui.profile_screen.ProfileScreen

const val PROFILE_SCREEN_ROUTE = "profile_screen"
private const val TRANSITION_DURATION = 300
private const val FIRST_NAME_KEY = UserKeys.FIRST_NAME_KEY
private const val LAST_NAME_KEY = UserKeys.LAST_NAME_KEY
private const val PATRONYMIC_KEY = UserKeys.PATRONYMIC_KEY
private const val BIRTHDAY_KEY = UserKeys.BIRTHDAY_KEY

fun NavController.navigateToProfileScreen(
    firstName: String?,
    lastName: String?,
    patronymic: String?,
    birthday: String?,
) {
    this.navigate("$PROFILE_SCREEN_ROUTE/$firstName/$lastName/$patronymic/$birthday") {
        popUpTo(EDIT_PROFILE_SCREEN_ROUTE) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.profileScreen(
    navController: NavController
) {
    composable(
        route = "$PROFILE_SCREEN_ROUTE/{$FIRST_NAME_KEY}/{$LAST_NAME_KEY}/{$PATRONYMIC_KEY}/{$BIRTHDAY_KEY}",
        arguments = listOf(
            navArgument(FIRST_NAME_KEY) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(LAST_NAME_KEY) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(PATRONYMIC_KEY) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(BIRTHDAY_KEY) {
                type = NavType.StringType
                nullable = true
            }
        ),
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) }
    ) {

        val firstNameArgument = it.arguments?.getString(FIRST_NAME_KEY) ?: ""
        val lastNameArgument = it.arguments?.getString(LAST_NAME_KEY) ?: ""
        val patronymicArgument = it.arguments?.getString(PATRONYMIC_KEY) ?: ""
        val birthdayArgument = it.arguments?.getString(BIRTHDAY_KEY) ?: ""

        ProfileScreen(
            navController = navController,
            firstName = firstNameArgument,
            lastName = lastNameArgument,
            patronymic = patronymicArgument,
            birthday = birthdayArgument,
        )
    }
}