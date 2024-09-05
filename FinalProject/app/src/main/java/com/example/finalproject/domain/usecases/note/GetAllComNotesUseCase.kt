package com.example.finalproject.domain.usecases.note

import com.example.finalproject.data.repositories.NoteRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.ui.models.NotesVO
import javax.inject.Inject

class GetAllComNotesUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl,
    private val domainToViewMapper: DomainToViewMapper
) {

    suspend fun getAllCommunityNotes(): Result<NotesVO> {
        return noteRepositoryImpl.getAllNotes().mapCatching { notes ->
            val notesVOList = notes.notes.map { domainToViewMapper.run { it.toView() } }
            NotesVO(notes = notesVOList)
        }
    }

}