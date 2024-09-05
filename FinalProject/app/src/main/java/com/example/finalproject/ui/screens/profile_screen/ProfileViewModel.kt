package com.example.finalproject.ui.screens.profile_screen

import androidx.lifecycle.ViewModel
import com.example.finalproject.domain.usecases.auth.LogoutUseCase
import com.example.finalproject.domain.usecases.note.GetAllLocalNotesUseCase
import com.example.finalproject.domain.usecases.profile.GetProfileUseCase
import com.example.finalproject.domain.usecases.profile.PhoneVisibilityUseCase
import com.example.finalproject.ui.models.NotesVO
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getAllLocalNotesUseCase: GetAllLocalNotesUseCase,
    private val phoneVisibilityUseCase: PhoneVisibilityUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel(), ContainerHost<ProfileState, ProfileSideEffect> {

    override val container = container<ProfileState, ProfileSideEffect>(ProfileState())

    init {
        loadProfile()
    }

    fun logout() {
        logoutUseCase.logout()
    }

    // Загрузка профиля
    fun loadProfile() = intent {
        reduce { state.copy(isLoading = true) }

        // Получаем локальные заметки
        val localNotesResult = getAllLocalNotesUseCase.getAllLocalNotes()

        // Получаем профиль
        val profileResult = getProfileUseCase.getProfile()

        profileResult.onSuccess { profile ->
            val notes = profile.notes.toMutableList()

            localNotesResult.onSuccess { localNotes ->
                notes.addAll(localNotes.notes)
            }.onFailure { error ->
                postSideEffect(ProfileSideEffect.ShowError("Failed to load local notes: ${error.message}"))
            }

            val updatedProfile = profile.copy(notes = notes)

            reduce {
                state.copy(
                    isLoading = false,
                    profile = updatedProfile,
                    notes = NotesVO(notes)
                )
            }
        }.onFailure { error ->
            postSideEffect(ProfileSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoading = false) }
        }
    }

    // Загрузка профиля
    fun changePhoneVisibility(visibility: Boolean) = intent {
        reduce { state.copy(isLoading = true) }

        val profileResult = phoneVisibilityUseCase.changePhoneVisibility(visibility)

        profileResult.onSuccess { profile ->
            reduce { state.copy(isLoading = false, profile = profile, isPhoneVisible = visibility) }
        }.onFailure { error ->
            postSideEffect(ProfileSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoading = false) }
        }
    }

    fun onAllCoursesClick(type: String) = intent {
        postSideEffect(ProfileSideEffect.NavigateToAllCourses(type))
    }

    fun onAllLocalNotesClick(type: String) = intent {
        postSideEffect(ProfileSideEffect.NavigateToAllNotes(type))
    }

    fun onLogoutClick() = intent {
        postSideEffect(ProfileSideEffect.Logout)
        logout()
    }

    fun onCourseClick(courseId: String, type: String) = intent {
        postSideEffect(
            ProfileSideEffect.NavigateToCourseDetail(
                courseId = courseId,
                type = type
            )
        )
    }

    fun onNoteCLick(localNoteId: String, type: String) = intent {
        postSideEffect(
            ProfileSideEffect.NavigateToNoteDetail(
                noteId = localNoteId,
                type = type
            )
        )
    }
}