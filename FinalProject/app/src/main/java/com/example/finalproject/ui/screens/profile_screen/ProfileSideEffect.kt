package com.example.finalproject.ui.screens.profile_screen

sealed class ProfileSideEffect {
    data class ShowError(val message: String) : ProfileSideEffect()

    data class NavigateToAllCourses(val type: String) : ProfileSideEffect()
    data class NavigateToAllNotes(val type: String) : ProfileSideEffect()
    data class NavigateToProfiles(val type: String) : ProfileSideEffect()

    data class NavigateToCourseDetail(val courseId: String, val type: String) : ProfileSideEffect()
    data class NavigateToNoteDetail(val noteId: String, val type: String) : ProfileSideEffect()

    object Logout : ProfileSideEffect()
}