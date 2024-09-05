package com.example.finalproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.profileNavGraph(
    profileScreenContent: @Composable () -> Unit,
    showAllScreen: @Composable (type: String) -> Unit,
    detailScreen: @Composable (type: String, id: String) -> Unit,
    addNoteContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.ProfilePage.route,
        route = Screen.ProfileTab.route
    ) {

        composable(Screen.ProfilePage.route) {
            profileScreenContent()
        }

        composable(
            route = Screen.ShowAllPage.route
        ) {
            val type = it.arguments?.getString(Screen.KEY_SHOW_ALL_TYPE) ?: ""
            showAllScreen(type)
        }

        composable(
            route = Screen.DetailPage.route
        ) {
            val type = it.arguments?.getString(Screen.KEY_SHOW_ALL_TYPE) ?: ""
            val id = it.arguments?.getString(Screen.KEY_ID) ?: ""
            detailScreen(type, id)
        }

        composable(Screen.AddNotePage.route) {
            addNoteContent()
        }
    }
}