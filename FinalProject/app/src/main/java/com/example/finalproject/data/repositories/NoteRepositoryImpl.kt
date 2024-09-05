package com.example.finalproject.data.repositories

import com.example.finalproject.data.api.ApiService
import com.example.finalproject.data.mapper.DataToDomainMapper
import com.example.finalproject.data.models.notes.CommentNoteRequest
import com.example.finalproject.data.models.notes.LocalNoteEntityDTO
import com.example.finalproject.data.models.notes.PostNoteRequest
import com.example.finalproject.data.storage.NoteDao
import com.example.finalproject.domain.models.Note
import com.example.finalproject.domain.models.Notes
import com.example.finalproject.domain.repositories.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataToDomainMapper: DataToDomainMapper,
    private val noteDao: NoteDao
) : NoteRepository {

    //communityNotes
    override suspend fun getAllNotes(): Result<Notes> {
        return try {
            val response = apiService.getAllNotes()
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun postNote(note: PostNoteRequest): Result<Note> {
        return try {
            val response = apiService.postNote(note)
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getNoteById(noteId: String): Result<Note> {
        return try {
            val response = apiService.getNoteById(noteId)
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun commentNote(
        noteId: String,
        comment: CommentNoteRequest
    ): Result<Note> {
        return try {
            val response = apiService.commentNote(
                noteId = noteId,
                request = comment
            )
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    //localNotes

    override suspend fun getAllLocalNotes(): Result<Notes> {
        return try {
            val response =
                dataToDomainMapper.run { noteDao.getAllLocalNotes().map { it.toDomain() } }
            val result = Notes(notes = response)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveLocalNote(note: LocalNoteEntityDTO): Result<Boolean> {
        return try {
            noteDao.insertLocalNote(note)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getLocalNoteById(noteId: String): Result<Note> {
        return try {
            val response = dataToDomainMapper.run { noteDao.getLocalNoteById(noteId).toDomain() }
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteLocalNote(noteId: String): Result<Boolean> {
        return try {
            noteDao.deleteLocalNote(noteId)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun clearTable(): Result<Boolean> {
        return try {
            noteDao.clearTable()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}