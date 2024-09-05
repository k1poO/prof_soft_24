package com.example.finalproject.domain.models

data class Profiles(
    val profiles: List<ProfileItem>
)

data class ProfileItem(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String
)

data class Profile(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String,
    val role: Int,
    val phone: String,
    val registerDate: String,
    val courses: List<Course>,
    val notes: List<Note>
)