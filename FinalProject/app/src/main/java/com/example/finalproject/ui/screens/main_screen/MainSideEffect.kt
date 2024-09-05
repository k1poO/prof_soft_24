package com.example.finalproject.ui.screens.main_screen

sealed class MainSideEffect {
    data class ShowError(val message: String) : MainSideEffect()

    data class NavigateToAllCourses(val type: String) : MainSideEffect()
    data class NavigateToAllComNotes(val type: String) : MainSideEffect()
    data class NavigateToAllLocalNotes(val type: String) : MainSideEffect()

    data class NavigateToCourseDetail(val courseId: String, val type: String) : MainSideEffect()
    data class NavigateToLocalNoteDetail(val noteId: String, val type: String) : MainSideEffect()
    data class NavigateToComNoteDetail(val noteId: String, val type: String) : MainSideEffect()
}