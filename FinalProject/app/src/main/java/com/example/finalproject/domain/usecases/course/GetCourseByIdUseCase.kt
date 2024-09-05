package com.example.finalproject.domain.usecases.course

import com.example.finalproject.data.repositories.CourseRepositoryImpl
import com.example.finalproject.domain.models.Course
import javax.inject.Inject

class GetCourseByIdUseCase @Inject constructor(
    private val courseRepositoryImpl: CourseRepositoryImpl
) {

    suspend fun getCourseById(courseId: String): Result<Course> {
        return courseRepositoryImpl.getCourseById(courseId)
    }
}