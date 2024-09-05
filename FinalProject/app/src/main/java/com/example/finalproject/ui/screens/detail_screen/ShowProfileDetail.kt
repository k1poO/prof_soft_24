package com.example.finalproject.ui.screens.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.finalproject.R
import com.example.finalproject.ui.components.CardHeader
import com.example.finalproject.ui.components.CoilImage
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
fun ShowProfileDetail(
    profile: ProfileVO,
    onAllCoursesClick: (type: String) -> Unit,
    onAllNotesClick: (type: String) -> Unit,
    onCourseClick: (courseId: String, type: String) -> Unit,
    onNoteClick: (noteId: String, type: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        Box(modifier = Modifier.fillWidthOfParent(16.dp)) {
            UserInfoCard(profile = profile)
        }


        CardHeader(
            modifier = Modifier.padding(bottom = 12.dp, top = 20.dp),
            title = stringResource(id = R.string.courses)
        ) {
            onAllCoursesClick(COURSE_TYPE)
        }
        if (profile.courses.isEmpty()) {
            EmptyCard()
        } else {
            PagerWithIndicator(
                listCourses = profile.courses
            ) { courseId ->
                onCourseClick(courseId, COURSE_TYPE)
            }
        }

        // Отображение локальных заметок
        CardHeader(
            modifier = Modifier.padding(bottom = 12.dp, top = 24.dp),
            title = stringResource(id = R.string.notes)
        ) {
            onAllNotesClick(LOCAL_NOTE_TYPE)
        }

        if (profile.notes.isNotEmpty()) {
            NoteCard(
                note = profile.notes.last()
            ) { noteId ->
                onNoteClick(noteId, LOCAL_NOTE_TYPE)
            }
        } else {
            EmptyCard(modifier = Modifier.height(112.dp))
        }

    }
}


@Composable
fun UserInfoCard(
    profile: ProfileVO,
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
                    text = profile.role,
                    style = LocalCustomTypography.current.text1416,
                    color = LocalCustomColors.current.Gray64
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier) {
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
    }
}