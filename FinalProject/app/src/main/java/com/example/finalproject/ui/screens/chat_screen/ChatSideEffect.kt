package com.example.finalproject.ui.screens.chat_screen

sealed class ChatSideEffect {
    data class ShowError(val message: String) : ChatSideEffect()
}