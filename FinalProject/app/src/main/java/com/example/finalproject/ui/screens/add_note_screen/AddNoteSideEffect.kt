package com.example.finalproject.ui.screens.add_note_screen

sealed class AddNoteSideEffect {
    data class ShowError(val message: String) : AddNoteSideEffect()

    data class NavigateToLocalNoteDetail(val noteId: String, val type: String) : AddNoteSideEffect()
}