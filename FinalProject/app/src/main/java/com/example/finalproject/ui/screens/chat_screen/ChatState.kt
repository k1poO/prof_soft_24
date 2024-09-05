package com.example.finalproject.ui.screens.chat_screen

import com.example.finalproject.ui.models.ChatVO

data class ChatState(
    val chat: ChatVO? = null,
    val error: String? = null,
    val currentUserId: String = "",
    val isLoading: Boolean = true
)