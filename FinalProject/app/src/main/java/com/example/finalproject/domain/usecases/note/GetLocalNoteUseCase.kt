package com.example.finalproject.domain.usecases.note

import com.example.finalproject.data.repositories.NoteRepositoryImpl
import com.example.finalproject.domain.models.Note
import javax.inject.Inject

class GetLocalNoteUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl
) {

    suspend fun getLocalNote(noteId: String): Result<Note> {
        return noteRepositoryImpl.getLocalNoteById(noteId = noteId)
    }
}