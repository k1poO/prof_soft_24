package com.example.finalproject.ui.models

import com.example.finalproject.domain.models.Course

data class ProfilesVO(
    val profiles: List<ProfileItemVO>
)

data class ProfileItemVO(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String
)

data class ProfileVO(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String,
    val role: String,
    val phone: String,
    val registerDate: String,
    val courses: List<Course>,
    val notes: List<NoteVO>
)