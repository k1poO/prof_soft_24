package com.example.finalproject.domain.usecases.note

import com.example.finalproject.data.repositories.NoteRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.ui.models.NoteVO
import javax.inject.Inject

class GetLocalNoteByIdUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl,
    private val domainToViewMapper: DomainToViewMapper
) {

    suspend fun getNoteById(noteId: String): Result<NoteVO> {
        return noteRepositoryImpl.getLocalNoteById(noteId = noteId).mapCatching {
            domainToViewMapper.run { it.toView() }
        }
    }
}