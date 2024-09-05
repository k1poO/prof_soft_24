package com.example.finalproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.chatNavGraph(
    chatScreenContent: @Composable () -> Unit,
    addNoteContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.ChatPage.route,
        route = Screen.ChatTab.route
    ) {

        composable(Screen.ChatPage.route) {
            chatScreenContent()
        }

        composable(Screen.AddNotePage.route) {
            addNoteContent()
        }
    }
}