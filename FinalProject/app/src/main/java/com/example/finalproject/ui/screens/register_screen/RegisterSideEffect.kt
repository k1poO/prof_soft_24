package com.example.finalproject.ui.screens.register_screen

sealed class RegisterSideEffect {
    data class ShowError(val message: String) : RegisterSideEffect()
    object NavigateToHome : RegisterSideEffect()
    object NavigateToAuth : RegisterSideEffect()
}