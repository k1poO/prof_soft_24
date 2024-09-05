package com.example.finalproject.ui.screens.profile_screen

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.finalproject.R
import com.example.finalproject.ui.components.CardHeader
import com.example.finalproject.ui.components.CoilImage
import com.example.finalproject.ui.components.CustomButton
import com.example.finalproject.ui.components.EmptyCard
import com.example.finalproject.ui.components.NoteCard
import com.example.finalproject.ui.components.PagerWithIndicator
import com.example.finalproject.ui.components.fillWidthOfParent
import com.example.finalproject.ui.models.ProfileVO
import com.example.finalproject.ui.screens.main_screen.COURSE_TYPE
import com.example.finalproject.ui.screens.main_screen.LOCAL_NOTE_TYPE
import com.example.finalproject.ui.theme.LocalCustomColors
import com.example.finalproject.ui.theme.LocalCustomTypography

@Composable
fun ProfileScreenContent(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNoteClick: (noteId: String, type: String) -> Unit,
    onCourseClick: (courseId: String, type: String) -> Unit,
    onAllCoursesClick: (type: String) -> Unit,
    onAllNotesClick: (type: String) -> Unit,
    onLogoutClick: () -> Unit,
    onProfilesClick: (type: String) -> Unit = {},
) {
    val state by viewModel.container.stateFlow.collectAsState()
    val context = LocalContext.current

    val profile = state.profile
    val notes = state.notes
    val isPhoneVisible = state.isPhoneVisible
    val isLoading = state.isLoading

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is ProfileSideEffect.NavigateToAllCourses -> {
                    onAllCoursesClick(sideEffect.type)
                }

                is ProfileSideEffect.NavigateToAllNotes -> {
                    onAllNotesClick(sideEffect.type)
                }

                is ProfileSideEffect.NavigateToCourseDetail -> {
                    onCourseClick(sideEffect.courseId, sideEffect.type)
                }

                is ProfileSideEffect.NavigateToNoteDetail -> {
                    onNoteClick(sideEffect.noteId, sideEffect.type)
                }

                is ProfileSideEffect.NavigateToProfiles -> {
                    onProfilesClick(sideEffect.type)
                }

                is ProfileSideEffect.ShowError -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

                ProfileSideEffect.Logout -> {
                    onLogoutClick()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {

            profile?.let {
                UserInfoCard(isVisible = isPhoneVisible, profile = it) { isVisible ->
                    viewModel.changePhoneVisibility(isVisible)
                }
            }

            CardHeader(
                modifier = Modifier.padding(bottom = 12.dp, top = 20.dp),
                title = stringResource(id = R.string.courses)
            ) {
                viewModel.onAllCoursesClick(COURSE_TYPE)
            }

            if (profile != null) {
                PagerWithIndicator(
                    listCourses = profile.courses
                ) { courseId ->
                    viewModel.onCourseClick(courseId = courseId, type = COURSE_TYPE)
                }
            } else {
                EmptyCard(modifier = Modifier.height(170.dp))
            }

            // Отображение локальных заметок
            CardHeader(
                modifier = Modifier.padding(bottom = 12.dp, top = 24.dp),
                title = stringResource(id = R.string.notes)
            ) {
                viewModel.onAllLocalNotesClick(LOCAL_NOTE_TYPE)
            }

            if (notes != null) {
                if (notes.notes.isNotEmpty()) {
                    NoteCard(
                        note = notes.notes.last()
                    ) { noteId ->
                        viewModel.onNoteCLick(localNoteId = noteId, type = LOCAL_NOTE_TYPE)
                    }
                } else {
                    EmptyCard(modifier = Modifier.height(112.dp))
                }
            }
        }

        CustomButton(
            text = stringResource(id = R.string.logout),
            shape = RectangleShape,
            onClick = { viewModel.onLogoutClick() },
        )
    }
}


@Composable
fun UserInfoCard(
    isVisible: Boolean,
    profile: ProfileVO,
    onToggleClick: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillWidthOfParent(16.dp)
            .background(LocalCustomColors.current.GrayD7)
            .padding(horizontal = 20.dp)
            .padding(bottom = 12.dp, top = 20.dp)
    ) {
        Row {
            CoilImage(
                link = profile.avatar, modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(start = 20.dp)
            ) {
                Text(
                    text = profile.name,
                    modifier = Modifier.padding(bottom = 6.dp),
                    style = LocalCustomTypography.current.profileTitle
                )
                Text(
                    text = profile.surname,
                    modifier = Modifier.padding(bottom = 6.dp),
                    style = LocalCustomTypography.current.profileTitle
                )
                Text(
                    text = profile.registerDate,
                    modifier = Modifier.padding(bottom = 6.dp),
                    style = LocalCustomTypography.current.text1416,
                    color = LocalCustomColors.current.Gray64
                )
                Text(
                    text = profile.role.toString(),
                    style = LocalCustomTypography.current.text1416,
                    color = LocalCustomColors.current.Gray64
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                val phoneNumber = stringResource(id = R.string.phone_number)
                Text(
                    text = ("$phoneNumber: ${profile.phone}"),
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = LocalCustomTypography.current.text1416,
                    color = LocalCustomColors.current.Black11
                )
                Text(
                    text = stringResource(id = R.string.show_my_phone),
                    style = LocalCustomTypography.current.text1416,
                    color = LocalCustomColors.current.Black11
                )
            }
            ToggleSwitch(
                checked = isVisible,
                onCheckedChange = { isChecked ->
                    onToggleClick(isChecked)
                }
            )
        }
    }
}

@Composable
fun ToggleSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    val thumbOffset by animateDpAsState(targetValue = if (checked) 24.dp else 2.dp, label = "")

    // Внешний контейнер для всего переключателя
    Box(
        modifier = modifier
            .size(width = 42.dp, height = 22.dp)
            .background(
                color = if (checked) Color.Black else Color.Gray,
                shape = RoundedCornerShape(11.dp) // Округление краёв
            )
            .clip(RoundedCornerShape(11.dp)) // Обрезка для круглых углов
            .clickable { onCheckedChange(checked) }, // Добавляем обработку клика
        contentAlignment = Alignment.CenterStart
    ) {
        // Кружок переключателя
        Box(
            modifier = Modifier
                .size(16.dp) // Размер кружка 16x16
                .offset(x = thumbOffset) // Смещение в зависимости от состояния
                .background(color = Color.Yellow, shape = CircleShape) // Цвет и форма кружка
                .clip(CircleShape) // Обрезка для круглой формы
        )
    }
}

@Preview
@Composable
fun Preview() {
    var checked = true
    ToggleSwitch(checked = checked) {
        checked = it
    }
}

