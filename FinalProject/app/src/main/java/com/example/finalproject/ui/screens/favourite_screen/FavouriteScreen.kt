package com.example.finalproject.ui.screens.favourite_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.finalproject.ui.components.tool_bar.ToolBarType

@Composable
fun FavouriteScreen(
    isTopVisible: (ToolBarType) -> Unit,
    isBottomVisible: (Boolean) -> Unit,
    onNavigateToLocalNoteDetail: (noteId: String, type: String) -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        isTopVisible(
            ToolBarType(
                type = ToolBarType.SEARCH_TYPE,
                isVisible = true,
                title = "Избранное",
                isBackArrow = false,
                onBackClick = {}
            )
        )
        isBottomVisible(true)
    }
    FavouriteScreenContent(
        onNavigateToLocalNoteDetail = onNavigateToLocalNoteDetail
    )
}