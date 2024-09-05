package com.example.finalproject.ui.screens.detail_screen

import android.os.Message
import com.example.finalproject.domain.models.Course
import com.example.finalproject.domain.models.Note
import com.example.finalproject.ui.models.NoteVO
import com.example.finalproject.ui.models.ProfileVO

data class DetailState(
    val isLoading: Boolean = false,
    val course: Course? = null,
    val localNote: NoteVO? = null,
    val profile: ProfileVO? = null,
    val communityNote: NoteVO? = null,
    val errorMessage: String? = null
)