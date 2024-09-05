package com.example.finalproject.data.models.courses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseDTO(
    val description: String,
    val id: String,
    val tags: List<String>,
    val text: List<TextDTO>,
    val title: String
)