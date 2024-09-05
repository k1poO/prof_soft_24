package com.example.finalproject.ui.screens.favourite_screen

import androidx.lifecycle.ViewModel
import com.example.finalproject.domain.usecases.note.GetFavouriteNotesUseCase
import com.example.finalproject.ui.screens.show_all_screen.ShowAllSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FavouriteScreenViewModel @Inject constructor(
    private val getFavouriteNotesUseCase: GetFavouriteNotesUseCase
) : ViewModel(), ContainerHost<FavouriteState, FavouriteSideEffect> {

    override val container = container<FavouriteState, FavouriteSideEffect>(FavouriteState())

    init {
        getFavouriteNotes()
    }

    private fun getFavouriteNotes() = intent {
        reduce { state.copy(isLoading = true) }

        val result = getFavouriteNotesUseCase.getFavouriteNotes()

        result.onSuccess { favouriteNotes ->
            reduce { state.copy(isLoading = false, favouriteNotes = favouriteNotes) }
        }.onFailure { error ->
            postSideEffect(FavouriteSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoading = false) }
        }
    }

    fun onLocalNoteCLick(localNoteId: String, type: String) = intent {
        postSideEffect(
            FavouriteSideEffect.NavigateToLocalNoteDetail(
                noteId = localNoteId,
                type = type
            )
        )
    }
}