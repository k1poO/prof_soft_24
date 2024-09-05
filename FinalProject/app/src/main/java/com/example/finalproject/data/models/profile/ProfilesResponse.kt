package com.example.finalproject.data.models.profile

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfilesResponse(
    val data: List<ProfilesDTO>
)