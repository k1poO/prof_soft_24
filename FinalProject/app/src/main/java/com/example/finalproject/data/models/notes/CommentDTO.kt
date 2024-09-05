package com.example.finalproject.data.models.notes

import com.example.finalproject.data.models.chat.AuthorDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentDTO(
    val author: AuthorDTO,
    val id: String,
    val text: String
)