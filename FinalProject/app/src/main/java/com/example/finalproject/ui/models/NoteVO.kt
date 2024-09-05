package com.example.finalproject.ui.models

data class NotesVO(
    val notes: List<NoteVO>
)

data class NoteVO(
    val id: String,
    val title: String,
    val content: List<ContentVO>,
    val author: AuthorVO?,
    val date: String,
    val comments: List<CommentVO>,
    val isFavourite: Boolean = false
)

data class ContentVO(
    val image: String,
    val text: String
)

data class AuthorVO(
    val avatar: String,
    val id: String,
    val name: String,
    val role: String,
    val surname: String
)

data class CommentVO(
    val author: AuthorVO,
    val id: String,
    val text: String
)