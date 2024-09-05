package com.example.finalproject.ui.screens.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.finalproject.domain.usecases.course.GetLastCoursesUseCase
import com.example.finalproject.domain.usecases.note.GetLastComNoteUseCase
import com.example.finalproject.domain.usecases.note.GetLastLocalNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLastCoursesUseCase: GetLastCoursesUseCase,
    private val getLastComNoteUseCase: GetLastComNoteUseCase,
    private val getLastLocalNoteUseCase: GetLastLocalNoteUseCase
) : ViewModel(), ContainerHost<MainState, MainSideEffect> {

    override val container = container<MainState, MainSideEffect>(MainState())

    private val state: MainState
        get() = container.stateFlow.value

    fun retry() {
        loadCourses()
        loadCommunityNotes()
        loadLocalNotes()
    }

    // Загрузка курсов
    fun loadCourses() = intent {
        reduce { state.copy(isLoadingCourses = true) }

        val result = getLastCoursesUseCase.getLastCourses()
        result.onSuccess { courses ->
            reduce { state.copy(isLoadingCourses = false, courses = courses) }
            updateLoadingState()
        }.onFailure { error ->
            Log.d("ERROR", "loadCourses: ${error.message}")
            postSideEffect(MainSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoadingCourses = false) }
            updateLoadingState()
        }
    }

    // Загрузка заметок сообщества
    fun loadCommunityNotes() = intent {
        reduce { state.copy(isLoadingCommunityNotes = true) }

        val result = getLastComNoteUseCase.getLastNote()
        result.onSuccess { note ->
            val length = note.date.length
            val date = note.date.substring(startIndex = 0, endIndex = length - 4)
            val newNote = note.copy(date = date)
            reduce { state.copy(isLoadingCommunityNotes = false, communityNote = newNote) }
            updateLoadingState()
        }.onFailure { error ->
            Log.d("ERROR", "loadCommunityNotes: ${error.message}")
            postSideEffect(MainSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoadingCommunityNotes = false) }
            updateLoadingState()
        }
    }

    // Загрузка локальных заметок
    fun loadLocalNotes() = intent {
        reduce { state.copy(isLoadingLocalNotes = true) }

        val result = getLastLocalNoteUseCase.getLastLocalNote()
        result.onSuccess { note ->
            val length = note.date.length
            val date = note.date.substring(startIndex = 0, endIndex = length - 4)
            val newNote = note.copy(date = date)
            reduce { state.copy(isLoadingLocalNotes = false, localNote = newNote) }
            updateLoadingState()
        }.onFailure { error ->
            Log.d("ERROR", "loadLocalNotes: ${error.message}")
            postSideEffect(MainSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoadingLocalNotes = false) }
            updateLoadingState()
        }
    }

    private fun updateLoadingState() = intent {
        reduce { state.copy(isLoading = isLoading()) }
    }

    private fun isLoading() =
        state.isLoadingCourses || state.isLoadingCommunityNotes || state.isLoadingLocalNotes

    fun onAllCoursesClick(type: String) = intent {
        postSideEffect(MainSideEffect.NavigateToAllCourses(type))
    }

    fun onAllLocalNotesClick(type: String) = intent {
        postSideEffect(MainSideEffect.NavigateToAllLocalNotes(type))
    }

    fun onAllComNotesClick(type: String) = intent {
        postSideEffect(MainSideEffect.NavigateToAllComNotes(type))
    }

    fun onCourseClick(courseId: String, type: String) = intent {
        postSideEffect(
            MainSideEffect.NavigateToCourseDetail(
                courseId = courseId,
                type = type
            )
        )
    }

    fun onLocalNoteCLick(localNoteId: String, type: String) = intent {
        postSideEffect(
            MainSideEffect.NavigateToLocalNoteDetail(
                noteId = localNoteId,
                type = type
            )
        )
    }

    fun onComNoteClick(communityNoteId: String, type: String) = intent {
        postSideEffect(
            MainSideEffect.NavigateToComNoteDetail(
                noteId = communityNoteId,
                type = type
            )
        )
    }

}


