package com.example.finalproject.domain.usecases.course

import com.example.finalproject.data.repositories.CourseRepositoryImpl
import com.example.finalproject.domain.models.Courses
import javax.inject.Inject

class GetAllCoursesUseCase @Inject constructor(
    private val courseRepositoryImpl: CourseRepositoryImpl
) {

    suspend fun getAllCourses(): Result<Courses> {
        return courseRepositoryImpl.getAllCourses()
    }
}