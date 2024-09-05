package com.example.finalproject.data.models.chat

data class ChatDTO(
    val author: AuthorDTO,
    val date: String,
    val id: String,
    val message: String
)