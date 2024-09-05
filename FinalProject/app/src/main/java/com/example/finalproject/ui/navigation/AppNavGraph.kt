package com.example.finalproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph (
    navHostController: NavHostController,
    splashScreen: @Composable () -> Unit,
    authPage: @Composable () -> Unit,
    addNoteContent: @Composable () -> Unit,
    registerPage: @Composable () -> Unit,
    homeScreenContent: @Composable () -> Unit,
    showAllScreenContent: @Composable (type: String) -> Unit,
    detailScreen: @Composable (type: String, id: String) -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
    chatScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(Screen.SplashScreen.route) {
            splashScreen()
        }

        composable(Screen.AuthPage.route) {
            authPage()
        }

        composable(Screen.RegisterPage.route) {
            registerPage()
        }

        mainNavGraph(
            homeScreen = homeScreenContent,
            showAllScreen = showAllScreenContent,
            detailScreen = detailScreen,
            addNoteContent = addNoteContent
        )

        favouriteNavGraph(
            favouriteScreen = favouriteScreenContent,
            detailScreen = detailScreen,
            addNoteContent = addNoteContent
        )

        chatNavGraph(
            chatScreenContent = chatScreenContent,
            addNoteContent = addNoteContent
        )

        composable(Screen.ChatPage.route) {
            chatScreenContent()
        }

        profileNavGraph(
            profileScreenContent = profileScreenContent,
            detailScreen = detailScreen,
            showAllScreen = showAllScreenContent,
            addNoteContent = addNoteContent
        )
    }
}