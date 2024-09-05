package com.example.finalproject.data.models.profile

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfilesDTO(
    val id: String?,
    val name: String?,
    val surname: String?,
    val avatar: String?
)