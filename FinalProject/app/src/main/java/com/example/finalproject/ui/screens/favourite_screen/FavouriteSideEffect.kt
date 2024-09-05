package com.example.finalproject.ui.screens.favourite_screen

sealed class FavouriteSideEffect {
    data class ShowError(val message: String) : FavouriteSideEffect()
    data class NavigateToLocalNoteDetail(val noteId: String, val type: String) : FavouriteSideEffect()
}