package com.example.finalproject.data.models.notes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentDTO(
    val image: String?,
    val text: String?
)