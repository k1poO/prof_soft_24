package com.example.finalproject.domain.usecases.note

import com.example.finalproject.data.repositories.NoteRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.ui.models.NoteVO
import javax.inject.Inject

class GetLastLocalNoteUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl,
    private val domainToViewMapper: DomainToViewMapper
) {

    suspend fun getLastLocalNote(): Result<NoteVO> {
        val result = noteRepositoryImpl.getAllLocalNotes()

        return if (result.isSuccess) {
            val listNotes = result.getOrNull()?.notes
            if (!listNotes.isNullOrEmpty()) {
                Result.success(domainToViewMapper.run { listNotes.last().toView() })
            } else {
                Result.failure(NoSuchElementException("No local notes found"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Unknown error"))
        }
    }
}