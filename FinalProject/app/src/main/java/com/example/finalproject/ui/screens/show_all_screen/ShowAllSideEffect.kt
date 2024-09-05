package com.example.finalproject.ui.screens.show_all_screen

sealed class ShowAllSideEffect {
    data class ShowError(val message: String) : ShowAllSideEffect()

    data class NavigateToCourseDetail(val courseId: String, val type: String) : ShowAllSideEffect()
    data class NavigateToLocalNoteDetail(val noteId: String, val type: String) : ShowAllSideEffect()
    data class NavigateToComNoteDetail(val noteId: String, val type: String) : ShowAllSideEffect()
    data class NavigateToProfileDetail(val profileId: String, val type: String) : ShowAllSideEffect()
}