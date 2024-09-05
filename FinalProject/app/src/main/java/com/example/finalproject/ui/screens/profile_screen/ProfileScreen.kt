package com.example.finalproject.ui.screens.profile_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.finalproject.ui.components.tool_bar.ToolBarType
import com.example.finalproject.ui.screens.main_screen.PROFILE_SCREEN_TYPE

@Composable
fun ProfileScreen(
    onNoteClick: (noteId: String, type: String) -> Unit,
    onCourseClick: (courseId: String, type: String) -> Unit,
    onAllCoursesClick: (type: String) -> Unit,
    onAllNotesClick: (type: String) -> Unit,
    onProfilesClick: (type: String) -> Unit,
    isTopVisible: (ToolBarType) -> Unit,
    onLogoutClick: () -> Unit,
    isBottomVisible: (Boolean) -> Unit,
) {

    LaunchedEffect(key1 = Unit) {
        isTopVisible(
            ToolBarType(
                type = ToolBarType.PROFILE_TYPE,
                isVisible = true,
                title = "Профиль",
                isBackArrow = false,
                onIconClick = { onProfilesClick(PROFILE_SCREEN_TYPE) },
                onBackClick = {}
            )
        )
        isBottomVisible(true)
    }

    ProfileScreenContent(
        onNoteClick = onNoteClick,
        onCourseClick = onCourseClick,
        onAllCoursesClick = onAllCoursesClick,
        onAllNotesClick = onAllNotesClick,
        onProfilesClick = onProfilesClick,
        onLogoutClick = onLogoutClick,
    )
}