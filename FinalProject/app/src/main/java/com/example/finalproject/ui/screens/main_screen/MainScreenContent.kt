package com.example.finalproject.ui.screens.main_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.finalproject.R
import com.example.finalproject.ui.components.CardHeader
import com.example.finalproject.ui.components.EmptyCard
import com.example.finalproject.ui.components.ErrorScreen
import com.example.finalproject.ui.components.LoadingScreen
import com.example.finalproject.ui.components.NoteCard
import com.example.finalproject.ui.components.PagerWithIndicator

const val COURSE_TYPE = "courses"
const val COMMUNITY_NOTE_TYPE = "community_notes"
const val LOCAL_NOTE_TYPE = "local_notes"
const val PROFILE_SCREEN_TYPE = "profile_screen_type"

@Composable
fun MainScreenContent(
    viewModel: MainViewModel = hiltViewModel(),
    onAllCoursesClick: (type: String) -> Unit,
    onAllLocalNotesClick: (type: String) -> Unit,
    onAllComNotesClick: (type: String) -> Unit,
    onCourseClick: (courseId: String, type: String) -> Unit,
    onComNoteClick: (noteId: String, type: String) -> Unit,
    onLocalNoteClick: (noteId: String, type: String) -> Unit
) {

    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current

    // Инициализация загрузки данных
    LaunchedEffect(Unit) {
        viewModel.loadCourses()
        viewModel.loadCommunityNotes()
        viewModel.loadLocalNotes()

        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is MainSideEffect.NavigateToAllCourses -> onAllCoursesClick(sideEffect.type)
                is MainSideEffect.NavigateToAllComNotes -> onAllComNotesClick(sideEffect.type)
                is MainSideEffect.NavigateToAllLocalNotes -> onAllLocalNotesClick(sideEffect.type)
                is MainSideEffect.ShowError -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }

                is MainSideEffect.NavigateToCourseDetail -> onCourseClick(
                    sideEffect.courseId,
                    sideEffect.type
                )

                is MainSideEffect.NavigateToComNoteDetail -> onComNoteClick(
                    sideEffect.noteId,
                    sideEffect.type
                )

                is MainSideEffect.NavigateToLocalNoteDetail -> onLocalNoteClick(
                    sideEffect.noteId,
                    sideEffect.type
                )
            }
        }
    }

    // Если данные загружаются, показываем индикатор загрузки
    if (state.isLoading) {
        LoadingScreen()
    } else if (state.error != null) {
        ErrorScreen {
            viewModel.retry()
        }
    } else {

        // Отображаем контент, если данные загружены
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(vertical = 20.dp, horizontal = 16.dp)
        ) {

            // Отображение курсов
            CardHeader(
                modifier = Modifier.padding(bottom = 12.dp),
                title = stringResource(id = R.string.your_courses)
            ) {
                viewModel.onAllCoursesClick(COURSE_TYPE)
            }

            if (state.courses.isEmpty()) {
                EmptyCard()
            } else {
                PagerWithIndicator(
                    listCourses = state.courses
                ) { courseId ->
                    viewModel.onCourseClick(courseId = courseId, type = COURSE_TYPE)
                }
            }

            // Отображение локальных заметок
            CardHeader(
                modifier = Modifier.padding(bottom = 12.dp, top = 24.dp),
                title = stringResource(id = R.string.your_notes)
            ) {
                viewModel.onAllLocalNotesClick(LOCAL_NOTE_TYPE)
            }

            if (state.localNote == null) {
                EmptyCard()
            } else {
                state.localNote?.let { note ->
                    NoteCard(
                        note = note
                    ) { noteId ->
                        viewModel.onLocalNoteCLick(localNoteId = noteId, type = LOCAL_NOTE_TYPE)
                    }
                }
            }

            // Отображение заметок сообщества
            CardHeader(
                modifier = Modifier.padding(bottom = 12.dp, top = 20.dp),
                title = stringResource(id = R.string.community_notes)
            ) {
                viewModel.onAllComNotesClick(COMMUNITY_NOTE_TYPE)
            }

            if (state.communityNote == null) {
                EmptyCard()
            } else {
                state.communityNote?.let { note ->
                    NoteCard(
                        note = note
                    ) { noteId ->
                        viewModel.onComNoteClick(
                            communityNoteId = noteId,
                            type = COMMUNITY_NOTE_TYPE
                        )
                    }
                }
            }

        }
    }
}
