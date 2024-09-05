package com.example.finalproject.ui.screens.show_all_screen

import androidx.lifecycle.ViewModel
import com.example.finalproject.domain.usecases.course.GetAllCoursesUseCase
import com.example.finalproject.domain.usecases.note.GetAllComNotesUseCase
import com.example.finalproject.domain.usecases.note.GetAllLocalNotesUseCase
import com.example.finalproject.domain.usecases.profile.GetAllProfilesUseCase
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
class ShowAllViewModel @Inject constructor(
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
    private val getAllLocalNotesUseCase: GetAllLocalNotesUseCase,
    private val getAllComNotesUseCase: GetAllComNotesUseCase,
    private val getAllProfilesUseCase: GetAllProfilesUseCase,
) : ViewModel(), ContainerHost<ShowAllState, ShowAllSideEffect> {

    override val container = container<ShowAllState, ShowAllSideEffect>(ShowAllState())

    fun loadData(type: String) = intent {
        when (type) {
            LOCAL_NOTE_TYPE -> getAllLocalNotes()
            COMMUNITY_NOTE_TYPE -> getAllCommunityNotes()
            COURSE_TYPE -> getAllCourses()
            PROFILE_SCREEN_TYPE -> getAllProfiles()
        }
    }

    private fun getAllLocalNotes() = intent {
        reduce { state.copy(isLoadingLocalNotes = true) }

        val result = getAllLocalNotesUseCase.getAllLocalNotes()

        result.onSuccess { localNotes ->
            reduce { state.copy(isLoadingLocalNotes = false, localNotes = localNotes) }
        }.onFailure { error ->
            postSideEffect(ShowAllSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoadingLocalNotes = false) }
        }
    }

    private fun getAllCommunityNotes() = intent {
        reduce { state.copy(isLoadingCommunityNotes = true) }

        val result = getAllComNotesUseCase.getAllCommunityNotes()

        result.onSuccess { communityNotes ->
            reduce { state.copy(isLoadingCommunityNotes = false, communityNotes = communityNotes) }
        }.onFailure { error ->
            postSideEffect(ShowAllSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoadingCommunityNotes = false) }
        }
    }

    private fun getAllCourses() = intent {
        reduce { state.copy(isLoadingCourses = true) }

        val result = getAllCoursesUseCase.getAllCourses()

        result.onSuccess { courses ->
            reduce { state.copy(isLoadingCourses = false, courses = courses) }
        }.onFailure { error ->
            postSideEffect(ShowAllSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoadingCourses = false) }
        }
    }

    private fun getAllProfiles() = intent {
        reduce { state.copy(isLoadingProfiles = true) }

        val result = getAllProfilesUseCase.getAllProfiles()

        result.onSuccess { profiles ->
            reduce { state.copy(isLoadingProfiles = false, profiles = profiles) }
        }.onFailure { error ->
            postSideEffect(ShowAllSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoadingProfiles = false) }
        }
    }

    fun onCourseClick(courseId: String, type: String) = intent {
        postSideEffect(
            ShowAllSideEffect.NavigateToCourseDetail(
                courseId = courseId,
                type = type
            )
        )
    }

    fun onLocalNoteCLick(localNoteId: String, type: String) = intent {
        postSideEffect(
            ShowAllSideEffect.NavigateToLocalNoteDetail(
                noteId = localNoteId,
                type = type
            )
        )
    }

    fun onComNoteClick(communityNoteId: String, type: String) = intent {
        postSideEffect(
            ShowAllSideEffect.NavigateToComNoteDetail(
                noteId = communityNoteId,
                type = type
            )
        )
    }

    fun onProfileClick(profileId: String, type: String) = intent {
        postSideEffect(
            ShowAllSideEffect.NavigateToProfileDetail(
                profileId = profileId,
                type = type
            )
        )
    }
}