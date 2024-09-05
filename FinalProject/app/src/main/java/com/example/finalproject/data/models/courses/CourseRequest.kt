package com.example.finalproject.data.models.courses

data class CourseRequest(
    val description: String,
    val tags: List<String>,
    val textDTO: List<TextDTO>,
    val title: String
)