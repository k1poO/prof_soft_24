package com.example.finalproject.domain.models

data class Chat(
    val listChatItems: List<ChatItem>
)

data class ChatItem(
    val author: Author,
    val date: String,
    val id: String,
    val message: String
)