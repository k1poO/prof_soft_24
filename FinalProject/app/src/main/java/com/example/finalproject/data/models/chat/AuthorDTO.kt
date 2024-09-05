package com.example.finalproject.data.models.chat

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorDTO(
    val avatar: String?,
    val id: String?,
    val name: String?,
    val role: String?,
    val surname: String?
)