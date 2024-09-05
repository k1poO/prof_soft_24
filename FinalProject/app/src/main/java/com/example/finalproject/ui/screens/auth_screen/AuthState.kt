package com.example.finalproject.ui.screens.auth_screen

data class AuthState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean? = null,
    val errorMessage: String? = null
)