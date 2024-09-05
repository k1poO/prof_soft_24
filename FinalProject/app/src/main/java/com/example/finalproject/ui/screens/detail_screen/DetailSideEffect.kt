package com.example.finalproject.ui.screens.detail_screen


sealed class DetailSideEffect {
    data class ShowError(val message: String) : DetailSideEffect()
}