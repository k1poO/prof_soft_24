package com.example.finalproject.ui.models


data class ChatVO(
    val listChatItems: List<ChatItemVO>
)

data class ChatItemVO(
    val author: AuthorVO,
    val date: String,
    val id: String,
    val message: String
)