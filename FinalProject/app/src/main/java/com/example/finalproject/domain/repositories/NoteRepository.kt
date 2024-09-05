package com.example.finalproject.domain.repositories

import com.example.finalproject.data.models.notes.CommentNoteRequest
import com.example.finalproject.data.models.notes.LocalNoteEntityDTO
import com.example.finalproject.data.models.notes.NoteDTO
import com.example.finalproject.data.models.notes.PostNoteRequest
import com.example.finalproject.domain.models.Note
import com.example.finalproject.domain.models.Notes

interface NoteRepository {

    suspend fun getAllNotes(): Result<Notes>

    suspend fun postNote(note: PostNoteRequest): Result<Note>

    suspend fun getNoteById(noteId: String): Result<Note>

    suspend fun commentNote(
        noteId: String,
        comment: CommentNoteRequest
    ): Result<Note>

    suspend fun getAllLocalNotes(): Result<Notes>

    suspend fun getLocalNoteById(noteId: String): Result<Note>

    suspend fun saveLocalNote(note: LocalNoteEntityDTO): Result<Boolean>

    suspend fun deleteLocalNote(noteId: String): Result<Boolean>

    suspend fun clearTable(): Result<Boolean>
}