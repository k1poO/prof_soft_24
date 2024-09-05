package com.example.finalproject.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalproject.data.models.notes.LocalNoteEntityDTO
import com.example.finalproject.data.models.notes.NoteDTO

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    suspend fun getAllLocalNotes(): List<LocalNoteEntityDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalNote(note: LocalNoteEntityDTO)

    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getLocalNoteById(noteId: String): LocalNoteEntityDTO

    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun deleteLocalNote(noteId: String)

    @Query("DELETE FROM notes")
    suspend fun clearTable()
}