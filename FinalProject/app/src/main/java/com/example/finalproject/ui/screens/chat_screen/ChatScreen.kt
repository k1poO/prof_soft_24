package com.example.finalproject.ui.screens.chat_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.finalproject.ui.components.tool_bar.ToolBarType

@Composable
fun ChatScreen(
    isTopVisible: (ToolBarType) -> Unit,
    isBottomVisible: (Boolean) -> Unit,
) {

    LaunchedEffect(key1 = Unit) {
        isTopVisible(
            ToolBarType(
                type = ToolBarType.SEARCH_TYPE,
                isVisible = true,
                title = "Чат",
                isBackArrow = false,
                onBackClick = {}
            )
        )
        isBottomVisible(true)
    }

    ChatScreenContent()
}