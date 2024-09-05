package com.example.finalproject.ui.screens.show_all_screen

import com.example.finalproject.domain.models.Courses
import com.example.finalproject.domain.models.Notes
import com.example.finalproject.domain.models.Profiles
import com.example.finalproject.ui.models.NotesVO

data class ShowAllState(
    val isLoadingCourses: Boolean = false,
    val courses: Courses? = null,
    val isLoadingCommunityNotes: Boolean = false,
    val communityNotes: NotesVO? = null,
    val isLoadingLocalNotes: Boolean = false,
    val localNotes: NotesVO? = null,
    val isLoadingProfiles: Boolean = false,
    val profiles: Profiles? = null,
    val error: String? = null,
    val isLoading: Boolean = true
)
