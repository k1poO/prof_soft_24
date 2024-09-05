package com.example.finalproject.domain.usecases.note

import com.example.finalproject.data.repositories.NoteRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToDataMapper
import com.example.finalproject.domain.models.Note
import javax.inject.Inject

class SaveLocalNoteUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl,
    private val domainToDataMapper: DomainToDataMapper
) {

    suspend fun saveLocalNote(note: Note): Result<Boolean> {
        val localNote = domainToDataMapper.run { note.toLocalData() }
        return noteRepositoryImpl.saveLocalNote(localNote)
    }

    suspend fun clear(): Result<Boolean> {
        return noteRepositoryImpl.clearTable()
    }
}