package com.example.finalproject.data.models.authorization

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponseDTO (
    val data: TokenDataDTO
)