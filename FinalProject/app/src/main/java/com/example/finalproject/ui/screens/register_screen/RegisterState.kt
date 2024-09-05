package com.example.finalproject.ui.screens.register_screen

data class RegisterState(
    val isLoading: Boolean = false,
    val isRegister: Boolean? = null,
    val errorMessage: String? = null
)