package com.example.finalproject.data.models.profile

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleProfileResponse(
    val data: SingleProfileDTO
)