package com.example.finalproject.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.finalproject.data.models.notes.LocalNoteEntityDTO
import com.example.finalproject.data.models.notes.NoteDTO

@Database(entities = [LocalNoteEntityDTO::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}