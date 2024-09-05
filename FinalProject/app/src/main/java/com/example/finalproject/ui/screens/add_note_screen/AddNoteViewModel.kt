package com.example.finalproject.ui.screens.add_note_screen

import androidx.lifecycle.ViewModel
import com.example.finalproject.domain.usecases.note.GetFavouriteNotesUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(
    private val getFavouriteNotesUseCase: GetFavouriteNotesUseCase
) : ViewModel(), ContainerHost<AddNoteState, AddNoteSideEffect> {

    override val container = container<AddNoteState, AddNoteSideEffect>(AddNoteState())


}