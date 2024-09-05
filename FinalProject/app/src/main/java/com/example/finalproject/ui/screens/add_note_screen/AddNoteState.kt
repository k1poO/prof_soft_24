package com.example.finalproject.ui.screens.add_note_screen

import com.example.finalproject.domain.models.Note

data class AddNoteState(
    val localNote: Note? = null,
    val error: String? = null,
    val isLoading: Boolean = true
)