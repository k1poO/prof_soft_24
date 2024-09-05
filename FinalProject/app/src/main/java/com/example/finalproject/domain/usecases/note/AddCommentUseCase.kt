package com.example.finalproject.domain.usecases.note

import com.example.finalproject.data.repositories.NoteRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToDataMapper
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.domain.models.Note
import com.example.finalproject.ui.models.NoteVO
import javax.inject.Inject

class AddCommentUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl,
    private val domainToDataMapper: DomainToDataMapper,
    private val domainToViewMapper: DomainToViewMapper
) {

    suspend fun commentNote(
        noteId: String,
        comment: String
    ): Result<NoteVO> {
        val request = domainToDataMapper.commentNoteRequest(comment)
        return noteRepositoryImpl.commentNote(noteId, request).mapCatching {
            domainToViewMapper.run { it.toView() }
        }
    }
}