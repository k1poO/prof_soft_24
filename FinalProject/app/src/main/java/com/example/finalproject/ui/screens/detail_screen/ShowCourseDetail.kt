package com.example.finalproject.ui.screens.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.finalproject.R
import com.example.finalproject.domain.models.Course
import com.example.finalproject.ui.components.CoilImage
import com.example.finalproject.ui.components.fillWidthOfParent
import com.example.finalproject.ui.theme.LocalCustomColors
import com.example.finalproject.ui.theme.LocalCustomTypography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShowCourseDetail(course: Course) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillWidthOfParent(16.dp)
                .fillMaxWidth()
                .background(LocalCustomColors.current.MainYellow)
                .padding(top = 44.dp, bottom = 12.dp)
                .padding(horizontal = 16.dp)
        ) {
            Text(text = course.title, style = LocalCustomTypography.current.title2832)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = course.description,
                style = LocalCustomTypography.current.text1214,
                color = LocalCustomColors.current.Gray33,
                maxLines = 1,
                modifier = Modifier
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.themes),
            style = LocalCustomTypography.current.text1619
        )
        FlowColumn {
            for (themes in course.tags) {
                if (themes != "") {
                    Text(
                        text = "•  $themes",
                        style = LocalCustomTypography.current.text1214,
                        color = LocalCustomColors.current.Gray64,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.lesson),
            style = LocalCustomTypography.current.text1619
        )
        FlowColumn {
            for (text in course.text) {
                Text(
                    text = text.text,
                    style = LocalCustomTypography.current.text1416,
                    color = LocalCustomColors.current.Gray64
                )
                if (text.image != "") {
                    CoilImage(
                        link = text.image,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RectangleShape)
                    )
                }
            }
        }
    }
}
