package com.example.finalproject.data.models.notes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotesResponse(
    val data: List<NoteDTO>
)