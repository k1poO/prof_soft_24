package com.example.finalproject.domain.repositories

import com.example.finalproject.data.models.courses.AllCourseResponse
import com.example.finalproject.data.models.courses.CourseRequest
import com.example.finalproject.data.models.courses.CourseResponse
import com.example.finalproject.domain.models.Course
import com.example.finalproject.domain.models.Courses

interface CourseRepository {

    suspend fun getAllCourses(): Result<Courses>

    suspend fun postCourse(request: CourseRequest): Result<Course>

    suspend fun getCourseById(courseId: String): Result<Course>
}