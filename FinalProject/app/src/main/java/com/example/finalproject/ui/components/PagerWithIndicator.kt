package com.example.finalproject.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.finalproject.domain.models.Course
import com.example.finalproject.ui.theme.LocalCustomColors
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerWithIndicator(
    modifier: Modifier = Modifier,
    listCourses: List<Course>,
    onCourseItemClick: (courseId: String) -> Unit
) {
    Column(
        modifier = modifier
    ) {

        val pagerState = rememberPagerState {
            listCourses.size
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillWidthOfParent(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 8.dp
        ) { page ->
            Card(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                                ).absoluteValue

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                when (page) {
                    0 -> {
                        CourseCard(course = listCourses[page]) { courseId ->
                            onCourseItemClick(courseId)
                        }
                    }

                    1 -> {
                        CourseCard(course = listCourses[page]) { courseId ->
                            onCourseItemClick(courseId)
                        }
                    }

                    2 -> {
                        CourseCard(course = listCourses[page]) { courseId ->
                            onCourseItemClick(courseId)
                        }
                    }

                    3 -> {
                        CourseCard(course = listCourses[page]) { courseId ->
                            onCourseItemClick(courseId)
                        }
                    }

                    4 -> {
                        CourseCard(course = listCourses[page]) { courseId ->
                            onCourseItemClick(courseId)
                        }
                    }

                    5 -> {
                        CourseCard(course = listCourses[page]) { courseId ->
                            onCourseItemClick(courseId)
                        }
                    }

                    6 -> {
                        CourseCard(course = listCourses[page]) { courseId ->
                            onCourseItemClick(courseId)
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) LocalCustomColors.current.Gray33 else LocalCustomColors.current.GrayD7
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(color)
                        .height(2.dp)
                        .weight(1f)
                )
            }
        }
    }
}

fun Modifier.fillWidthOfParent(parentPadding: Dp) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(
            constraints.copy(
                maxWidth = constraints.maxWidth + 2 * parentPadding.roundToPx(),
            ),
        )
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    },
)