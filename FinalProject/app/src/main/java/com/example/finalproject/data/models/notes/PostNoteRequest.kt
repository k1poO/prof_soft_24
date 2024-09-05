package com.example.finalproject.data.models.notes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostNoteRequest(
    val title: String,
    val contentDTO: ContentDTO
)