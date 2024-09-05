package com.example.finalproject.ui.screens.show_all_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.finalproject.ui.components.tool_bar.ToolBarType
import com.example.finalproject.ui.screens.main_screen.COMMUNITY_NOTE_TYPE
import com.example.finalproject.ui.screens.main_screen.COURSE_TYPE
import com.example.finalproject.ui.screens.main_screen.LOCAL_NOTE_TYPE

@Composable
fun ShowAllScreen(
    type: String,
    isTopVisible: (ToolBarType) -> Unit,
    isBottomVisible: (Boolean) -> Unit,
    onNavigateToCourseDetail: (id: String, type: String) -> Unit,
    onNavigateToLocalNoteDetail: (id: String, type: String) -> Unit,
    onNavigateToComNoteDetail: (id: String, type: String) -> Unit,
    onNavigateToProfileDetail: (id: String, type: String) -> Unit,
    onBackClick: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        val title = when (type) {
            COURSE_TYPE -> {
                "Все курсы"
            }

            COMMUNITY_NOTE_TYPE -> {
                "Заметки сообщества"
            }

            LOCAL_NOTE_TYPE -> {
                "Ваши заметки"
            }

            else -> {
                "Все пользователи"
            }
        }
        isTopVisible(
            ToolBarType(
                type = ToolBarType.SEARCH_TYPE,
                isVisible = true,
                title = title,
                isBackArrow = true,
                onBackClick = onBackClick
            )
        )
        isBottomVisible(true)
    }
    ShowAllScreenContent(
        type = type,
        onNavigateToCourseDetail = onNavigateToCourseDetail,
        onNavigateToLocalNoteDetail = onNavigateToLocalNoteDetail,
        onNavigateToComNoteDetail = onNavigateToComNoteDetail,
        onNavigateToProfileDetail = onNavigateToProfileDetail,
    )
}