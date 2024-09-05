package com.example.finalproject.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.finalproject.domain.models.Course
import com.example.finalproject.ui.theme.LocalCustomColors
import com.example.finalproject.ui.theme.LocalCustomTypography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    course: Course,
    onCourseClick: (courseId: String) -> Unit
) {
    val localColors = LocalCustomColors.current
    val localTypography = LocalCustomTypography.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(localColors.MainYellow)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = 175.dp,
                    color = localColors.Gray33
                )
            ) {
                onCourseClick(course.id)
            }
    ) {
        Text(
            text = course.title,
            modifier = Modifier
                .padding(bottom = 18.dp),
            style = localTypography.title2832
        )
        FlowRow {
            for (item in course.tags) {
                TagText(text = item)
            }
        }
    }
}

@Composable
fun TagText(text: String) {
    Box(
        modifier = Modifier
            .padding(end = 4.dp, bottom = 4.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                border = BorderStroke(1.dp, LocalCustomColors.current.GrayT33)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(text = text, style = LocalCustomTypography.current.text1214)
    }
}
