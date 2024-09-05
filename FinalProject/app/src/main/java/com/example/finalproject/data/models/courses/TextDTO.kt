package com.example.finalproject.data.models.courses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TextDTO(
    val image: String,
    val text: String
)