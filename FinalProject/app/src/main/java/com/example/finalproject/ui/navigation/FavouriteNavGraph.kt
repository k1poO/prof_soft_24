package com.example.finalproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.favouriteNavGraph(
    favouriteScreen: @Composable () -> Unit,
    addNoteContent: @Composable () -> Unit,
    detailScreen: @Composable (type: String, id: String) -> Unit,
) {
    navigation(
        startDestination = Screen.FavouritePage.route,
        route = Screen.FavouriteTab.route
    ) {

        composable(Screen.FavouritePage.route) {
            favouriteScreen()
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