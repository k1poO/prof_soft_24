package com.example.finalproject.data.storage

import androidx.room.TypeConverter
import com.example.finalproject.data.models.chat.AuthorDTO
import com.example.finalproject.data.models.notes.CommentDTO
import com.example.finalproject.data.models.notes.ContentDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromContentList(value: List<ContentDTO>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toContentList(value: String): List<ContentDTO> {
        val listType = object : TypeToken<List<ContentDTO>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCommentList(value: List<CommentDTO>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCommentList(value: String): List<CommentDTO> {
        val listType = object : TypeToken<List<CommentDTO>>() {}.type
        return Gson().fromJson(value, listType)
    }
}