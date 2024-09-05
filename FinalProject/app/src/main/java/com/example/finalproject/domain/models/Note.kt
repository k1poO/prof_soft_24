package com.example.finalproject.domain.models

data class Notes(
    val notes: List<Note>
)

data class Note(
    val id: String,
    val title: String,
    val content: List<Content>,
    val author: Author?,
    val date: String,
    val comments: List<Comment>,
    val isFavourite: Boolean = false
)

data class Content(
    val image: String,
    val text: String
)

data class Author(
    val avatar: String,
    val id: String,
    val name: String,
    val role: String,
    val surname: String
)

data class Comment(
    val author: Author,
    val id: String,
    val text: String
)