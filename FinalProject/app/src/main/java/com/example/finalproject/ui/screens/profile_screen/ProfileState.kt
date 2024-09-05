package com.example.finalproject.ui.screens.profile_screen

import com.example.finalproject.ui.models.NotesVO
import com.example.finalproject.ui.models.ProfileVO

data class ProfileState(
    val isLoading: Boolean = false,
    val profile: ProfileVO? = null,
    val notes: NotesVO? = null,
    val isPhoneVisible: Boolean = false,
    val error: String? = null
)