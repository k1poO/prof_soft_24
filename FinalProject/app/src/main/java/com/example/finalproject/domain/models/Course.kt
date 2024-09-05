package com.example.finalproject.domain.models

data class Courses(
    val courses: List<Course>
)

data class Course(
    val description: String,
    val id: String,
    val tags: List<String>,
    val text: List<Text>,
    val title: String
)

data class Text(
    val image: String,
    val text: String
)