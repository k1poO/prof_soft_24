package com.example.finalproject.domain.usecases.note

import com.example.finalproject.data.repositories.NoteRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.ui.models.NoteVO
import javax.inject.Inject

class GetLastComNoteUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl,
    private val domainToViewMapper: DomainToViewMapper
) {

    suspend fun getLastNote(): Result<NoteVO> {
        val result = noteRepositoryImpl.getAllNotes()

        return if (result.isSuccess) {
            val listNotes = result.getOrNull()?.notes
            if (!listNotes.isNullOrEmpty()) {
                Result.success(domainToViewMapper.run { listNotes.last().toView() } )
            } else {
                Result.failure(NoSuchElementException("No notes found"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Unknown error"))
        }
    }

}