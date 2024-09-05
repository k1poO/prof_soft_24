package com.example.finalproject.ui.screens.detail_screen

import androidx.lifecycle.ViewModel
import com.example.finalproject.domain.usecases.course.GetCourseByIdUseCase
import com.example.finalproject.domain.usecases.note.AddCommentUseCase
import com.example.finalproject.domain.usecases.note.GetComNoteByIdUseCase
import com.example.finalproject.domain.usecases.note.GetLocalNoteByIdUseCase
import com.example.finalproject.domain.usecases.profile.GetProfileByIdUseCase
import com.example.finalproject.ui.screens.main_screen.COMMUNITY_NOTE_TYPE
import com.example.finalproject.ui.screens.main_screen.COURSE_TYPE
import com.example.finalproject.ui.screens.main_screen.LOCAL_NOTE_TYPE
import com.example.finalproject.ui.screens.main_screen.PROFILE_SCREEN_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCourseByIdUseCase: GetCourseByIdUseCase,
    private val getLocalNoteByIdUseCase: GetLocalNoteByIdUseCase,
    private val getComNoteByIdUseCase: GetComNoteByIdUseCase,
    private val addCommentUseCase: AddCommentUseCase,
    private val getProfileByIdUseCase: GetProfileByIdUseCase
) : ViewModel(), ContainerHost<DetailState, DetailSideEffect> {

    override val container = container<DetailState, DetailSideEffect>(DetailState())

    // Метод для загрузки данных в зависимости от типа
    fun loadDetails(type: String, id: String) = when (type) {
        LOCAL_NOTE_TYPE -> getLocalNoteById(id)
        COURSE_TYPE -> getCourseById(id)
        COMMUNITY_NOTE_TYPE -> getCommunityNoteById(id)
        PROFILE_SCREEN_TYPE -> getProfileById(id)
        else -> Unit
    }

    private fun getLocalNoteById(noteId: String) = intent {
        reduce { state.copy(isLoading = true) }

        val result = getLocalNoteByIdUseCase.getNoteById(noteId = noteId)
        result.onSuccess { localNote ->
            reduce { state.copy(isLoading = false, localNote = localNote) }
        }.onFailure { error ->
            postSideEffect(DetailSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoading = false) }
        }
    }

    private fun getProfileById(profileId: String) = intent {
        reduce { state.copy(isLoading = true) }

        val result = getProfileByIdUseCase.getProfileById(userId = profileId)
        result.onSuccess { profile ->
            reduce { state.copy(isLoading = false, profile = profile) }
        }.onFailure { error ->
            postSideEffect(DetailSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoading = false) }
        }
    }

    private fun getCommunityNoteById(noteId: String) = intent {
        reduce { state.copy(isLoading = true) }

        val result = getComNoteByIdUseCase.getNoteById(noteId = noteId)
        result.onSuccess { communityNote ->
            reduce { state.copy(isLoading = false, communityNote = communityNote) }
        }.onFailure { error ->
            postSideEffect(DetailSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoading = false) }
        }
    }

    private fun getCourseById(courseId: String) = intent {
        reduce { state.copy(isLoading = true) }

        val result = getCourseByIdUseCase.getCourseById(courseId)
        result.onSuccess { course ->
            reduce { state.copy(isLoading = false, course = course) }
        }.onFailure { error ->
            postSideEffect(DetailSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoading = false) }
        }
    }

    fun addComment(noteId: String, text: String) = intent {
        reduce { state.copy(isLoading = true) }

        val result = addCommentUseCase.commentNote(noteId = noteId, comment = text)
        result.onSuccess { communityNote ->
            reduce { state.copy(isLoading = false, communityNote = communityNote) }
        }.onFailure { error ->
            postSideEffect(DetailSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoading = false) }
        }
    }
}