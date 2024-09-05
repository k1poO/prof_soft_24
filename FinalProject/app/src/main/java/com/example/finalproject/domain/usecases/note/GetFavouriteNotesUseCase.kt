package com.example.finalproject.domain.usecases.note

import com.example.finalproject.data.repositories.NoteRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.domain.models.Notes
import com.example.finalproject.ui.models.NotesVO
import javax.inject.Inject

class GetFavouriteNotesUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl,
    private val domainToViewMapper: DomainToViewMapper
) {

    suspend fun getFavouriteNotes(): Result<NotesVO> {
        val notes = noteRepositoryImpl.getAllLocalNotes().mapCatching { notes ->
            val notesVOList = notes.notes.map { domainToViewMapper.run { it.toView() } }
            NotesVO(notes = notesVOList)
        }
        val favouriteNotes = notes.onSuccess { listNotes ->
            listNotes.notes.filter { it.isFavourite }
        }

        return favouriteNotes
    }
}

