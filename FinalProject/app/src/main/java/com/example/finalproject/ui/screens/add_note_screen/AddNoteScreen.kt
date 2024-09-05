package com.example.finalproject.ui.screens.add_note_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.finalproject.ui.components.tool_bar.ToolBarType

@Composable
fun AddNoteScreen(
    isTopVisible: (ToolBarType) -> Unit,
    isBottomVisible: (Boolean) -> Unit,
    onBackClick: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        isTopVisible(
            ToolBarType(
                type = ToolBarType.BASE_TYPE,
                isVisible = true,
                title = "Новая заметка",
                isBackArrow = true,
                onBackClick = onBackClick
            )
        )
        isBottomVisible(true)
    }
}