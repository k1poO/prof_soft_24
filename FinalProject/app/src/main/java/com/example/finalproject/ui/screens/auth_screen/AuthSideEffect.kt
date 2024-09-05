package com.example.finalproject.ui.screens.auth_screen

sealed class AuthSideEffect {
    data class ShowError(val message: String) : AuthSideEffect()
    object NavigateToHome : AuthSideEffect()
    object NavigateToRegister : AuthSideEffect()
}