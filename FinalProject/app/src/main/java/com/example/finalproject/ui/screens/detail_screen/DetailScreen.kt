package com.example.finalproject.ui.screens.detail_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.finalproject.ui.components.ErrorScreen
import com.example.finalproject.ui.components.LoadingScreen
import com.example.finalproject.ui.components.tool_bar.ToolBarType
import com.example.finalproject.ui.screens.main_screen.COMMUNITY_NOTE_TYPE
import com.example.finalproject.ui.screens.main_screen.COURSE_TYPE
import com.example.finalproject.ui.screens.main_screen.LOCAL_NOTE_TYPE
import com.example.finalproject.ui.screens.main_screen.PROFILE_SCREEN_TYPE

@Composable
fun DetailScreen(
    type: String,
    id: String,
    isTopVisible: (ToolBarType) -> Unit,
    isBottomVisible: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        val title = when (type) {
            COURSE_TYPE -> {
                "Лекция 1"
            }

            COMMUNITY_NOTE_TYPE -> {
                "Заметка"
            }

            LOCAL_NOTE_TYPE -> {
                "Заметка"
            }

            else -> {
                "Профиль"
            }
        }
        isTopVisible(
            ToolBarType(
                type = ToolBarType.FAVOURITE_TYPE,
                isVisible = true,
                title = title,
                isBackArrow = true,
                onBackClick = onBackClick
            )
        )
        isBottomVisible(false)
    }

    // Запускаем загрузку данных при первом рендеринге экрана
    LaunchedEffect(key1 = id) {
        viewModel.loadDetails(type, id)
    }

    // Обработка побочных эффектов
    LaunchedEffect(key1 = Unit) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is DetailSideEffect.ShowError -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    when {
        state.isLoading -> {
            // Отображение индикатора загрузки
            LoadingScreen()
        }

        state.localNote != null && type == LOCAL_NOTE_TYPE -> {
            ShowLocalNoteDetail(localNote = state.localNote!!)
        }

        state.course != null && type == COURSE_TYPE -> {
            ShowCourseDetail(course = state.course!!)
        }

        state.communityNote != null && type == COMMUNITY_NOTE_TYPE -> {
            ShowCommunityNoteDetail(comNote = state.communityNote!!, viewModel = viewModel)
        }

        state.profile != null && type == PROFILE_SCREEN_TYPE -> {
            Log.d("TAG", "DetailScreen: ${state.profile}")
            val profile = state.profile
            if (profile != null) {
                ShowProfileDetail(
                    profile = profile,
                    onAllCoursesClick = {

                    },
                    onAllNotesClick = {

                    },
                    onCourseClick = { courseId, courseType ->
                        viewModel.loadDetails(courseType, courseId)
                    },
                    onNoteClick = { noteId, noteType ->
                        viewModel.loadDetails(noteType, noteId)
                    }
                )
            }
        }

        else -> {
            ErrorScreen {
                viewModel.loadDetails(type, id)
            }
        }
    }
}