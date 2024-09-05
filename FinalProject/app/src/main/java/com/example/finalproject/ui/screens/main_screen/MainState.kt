package com.example.finalproject.ui.screens.main_screen

import com.example.finalproject.domain.models.Course
import com.example.finalproject.domain.models.Note
import com.example.finalproject.ui.models.NoteVO

data class MainState(
    val isLoadingCourses: Boolean = false,
    val courses: List<Course> = emptyList(),
    val isLoadingCommunityNotes: Boolean = false,
    val communityNote: NoteVO? = null,
    val isLoadingLocalNotes: Boolean = false,
    val localNote: NoteVO? = null,
    val error: String? = null,
    val isLoading: Boolean = true
)