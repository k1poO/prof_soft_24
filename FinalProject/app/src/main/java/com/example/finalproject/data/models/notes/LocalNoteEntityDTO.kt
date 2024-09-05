package com.example.finalproject.data.models.notes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "notes")
@JsonClass(generateAdapter = true)
data class LocalNoteEntityDTO(
    @PrimaryKey val id: String,
    val title: String,
    val content: List<ContentDTO>,
    val isFavourite: Boolean,
    val date: String,
    val comments: List<CommentDTO>,
)