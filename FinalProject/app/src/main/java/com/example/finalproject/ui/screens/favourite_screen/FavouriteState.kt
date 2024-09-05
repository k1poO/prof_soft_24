package com.example.finalproject.ui.screens.favourite_screen

import com.example.finalproject.ui.models.NotesVO


data class FavouriteState(
    val favouriteNotes: NotesVO? = null,
    val error: String? = null,
    val isLoading: Boolean = true
)
