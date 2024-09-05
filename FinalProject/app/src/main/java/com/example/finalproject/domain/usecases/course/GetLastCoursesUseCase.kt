package com.example.finalproject.domain.usecases.course

import com.example.finalproject.data.repositories.CourseRepositoryImpl
import com.example.finalproject.domain.models.Course
import javax.inject.Inject

class GetLastCoursesUseCase @Inject constructor(
    private val courseRepositoryImpl: CourseRepositoryImpl
) {

    suspend fun getLastCourses(): Result<List<Course>> {
        val result = courseRepositoryImpl.getAllCourses()

        return if (result.isSuccess) {
            val listCourses = result.getOrNull()?.courses
            if (!listCourses.isNullOrEmpty()) {
                Result.success(listCourses.takeLast(6).reversed())
            } else {
                Result.failure(NoSuchElementException("No courses found"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Unknown error"))
        }
    }
}