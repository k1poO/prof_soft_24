package com.example.finalproject.data.models.profile

import com.example.finalproject.data.models.courses.CourseDTO
import com.example.finalproject.data.models.notes.NoteDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleProfileDTO(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String,
    val role: Int,
    val phone: String?,
    val registerDate: String,
    val courses: List<CourseDTO>,
    val notes: List<NoteDTO>
)