package com.example.finalproject.ui.screens.main_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.finalproject.ui.components.tool_bar.ToolBarType
import com.example.finalproject.ui.components.tool_bar.ToolBarType.Companion.SEARCH_TYPE

@Composable
fun MainScreen(
    paddingValues: PaddingValues,
    isTopVisible: (ToolBarType) -> Unit,
    isBottomVisible: (Boolean) -> Unit,
    onAllCoursesClick: (type: String) -> Unit,
    onAllLocalNotesClick: (type: String) -> Unit,
    onAllComNotesClick: (type: String) -> Unit,
    onCourseClick: (courseId: String, type: String) -> Unit,
    onComNoteClick: (noteId: String, type: String) -> Unit,
    onLocalNoteClick: (noteId: String, type: String) -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        isTopVisible(
            ToolBarType(
                type = SEARCH_TYPE,
                isVisible = true,
                title = "Главная",
                isBackArrow = false,
                onBackClick = {}
            )
        )
        isBottomVisible(true)
    }

    MainScreenContent(
        onAllComNotesClick = { type -> onAllComNotesClick(type) },
        onAllCoursesClick = { type -> onAllCoursesClick(type) },
        onAllLocalNotesClick = { type -> onAllLocalNotesClick(type) },
        onCourseClick = onCourseClick,
        onComNoteClick = onComNoteClick,
        onLocalNoteClick = onLocalNoteClick
    )
}