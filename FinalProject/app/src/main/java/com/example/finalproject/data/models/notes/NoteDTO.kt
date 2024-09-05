package com.example.finalproject.data.models.notes

import com.example.finalproject.data.models.chat.AuthorDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NoteDTO(
    val id: String?,
    val title: String?,
    val content: List<ContentDTO>?,
    val author: AuthorDTO?,
    val date: String?,
    val comments: List<CommentDTO>?,
)