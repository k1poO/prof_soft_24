package com.example.finalproject.data.models.authorization

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequest(
    val phone: String,
    val passwordHashed: String,
    val name: String,
    val surname: String,
    val avatar: String
)