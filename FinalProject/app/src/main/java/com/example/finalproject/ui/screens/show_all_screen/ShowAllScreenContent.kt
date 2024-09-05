package com.example.finalproject.ui.screens.show_all_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.finalproject.ui.components.CoilImage
import com.example.finalproject.ui.components.CourseCard
import com.example.finalproject.ui.components.LoadingScreen
import com.example.finalproject.ui.components.NoteCard
import com.example.finalproject.ui.screens.main_screen.COMMUNITY_NOTE_TYPE
import com.example.finalproject.ui.screens.main_screen.COURSE_TYPE
import com.example.finalproject.ui.screens.main_screen.LOCAL_NOTE_TYPE
import com.example.finalproject.ui.screens.main_screen.PROFILE_SCREEN_TYPE
import com.example.finalproject.ui.theme.LocalCustomColors

@Composable
fun ShowAllScreenContent(
    viewModel: ShowAllViewModel = hiltViewModel(),
    type: String,
    onNavigateToCourseDetail: (courseId: String, type: String) -> Unit,
    onNavigateToLocalNoteDetail: (localNoteId: String, type: String) -> Unit,
    onNavigateToComNoteDetail: (communityNoteId: String, type: String) -> Unit,
    onNavigateToProfileDetail: (profileId: String, type: String) -> Unit
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current

    // Загрузка данных при запуске
    LaunchedEffect(type) {
        viewModel.loadData(type)
    }

    // Обработка sideEffect
    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is ShowAllSideEffect.NavigateToCourseDetail -> onNavigateToCourseDetail(
                    sideEffect.courseId,
                    sideEffect.type
                )

                is ShowAllSideEffect.NavigateToLocalNoteDetail -> onNavigateToLocalNoteDetail(
                    sideEffect.noteId,
                    sideEffect.type
                )

                is ShowAllSideEffect.NavigateToComNoteDetail -> onNavigateToComNoteDetail(
                    sideEffect.noteId,
                    sideEffect.type
                )

                is ShowAllSideEffect.NavigateToProfileDetail -> onNavigateToProfileDetail(
                    sideEffect.profileId,
                    sideEffect.type
                )

                is ShowAllSideEffect.ShowError -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Индикатор загрузки
    if (state.isLoadingCourses || state.isLoadingLocalNotes || state.isLoadingCommunityNotes || state.isLoadingProfiles) {
        LoadingScreen()
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp)
                .fillMaxSize()
                .background(Color.White)
        ) {
            when (type) {
                LOCAL_NOTE_TYPE -> {
                    state.localNotes?.notes?.let { notes ->
                        items(notes.size) { index ->
                            NoteCard(
                                modifier = Modifier.padding(bottom = 16.dp),
                                note = notes[index]
                            ) { localNoteId ->
                                viewModel.onLocalNoteCLick(
                                    localNoteId = localNoteId,
                                    type = LOCAL_NOTE_TYPE
                                )
                            }
                        }
                    }
                }

                COURSE_TYPE -> {
                    state.courses?.courses?.let { courses ->
                        items(courses.size) { index ->
                            CourseCard(
                                modifier = Modifier.padding(bottom = 16.dp),
                                course = courses[index]
                            ) { courseId ->
                                viewModel.onCourseClick(courseId = courseId, type = COURSE_TYPE)
                            }
                        }
                    }
                }

                COMMUNITY_NOTE_TYPE -> {
                    state.communityNotes?.notes?.let { notes ->
                        items(notes.size) { index ->
                            NoteCard(
                                modifier = Modifier.padding(bottom = 16.dp),
                                note = notes[index]
                            ) { communityNoteId ->
                                viewModel.onComNoteClick(
                                    communityNoteId = communityNoteId,
                                    type = COMMUNITY_NOTE_TYPE
                                )
                            }
                        }
                    }
                }

                PROFILE_SCREEN_TYPE -> {
                    state.profiles?.profiles?.let { profiles ->
                        items(profiles.size) { index ->
                            Row(
                                modifier = Modifier
                                    .padding(bottom = 12.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(
                                            bounded = false,
                                            radius = 30.dp,
                                            color = LocalCustomColors.current.Gray33
                                        )
                                    ) {
                                        viewModel.onProfileClick(
                                            profileId = profiles[index].id,
                                            type = PROFILE_SCREEN_TYPE
                                        )
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CoilImage(
                                    link = profiles[index].avatar,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                )
                                val name = profiles[index].name
                                val surname = profiles[index].surname
                                Text(
                                    text = "$name $surname",
                                    modifier = Modifier.padding(start = 12.dp),
                                    style = TextStyle(fontSize = 20.sp, lineHeight = 23.sp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}