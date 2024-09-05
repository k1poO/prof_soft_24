package com.example.finalproject.data.repositories

import com.example.finalproject.data.api.ApiService
import com.example.finalproject.data.mapper.DataToDomainMapper
import com.example.finalproject.data.models.courses.CourseRequest
import com.example.finalproject.domain.models.Course
import com.example.finalproject.domain.models.Courses
import com.example.finalproject.domain.repositories.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataToDomainMapper: DataToDomainMapper,
) : CourseRepository {

    override suspend fun getAllCourses(): Result<Courses> {
        return try {
            val response = apiService.getAllCourses()
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun postCourse(request: CourseRequest): Result<Course> {
        return try {
            val response = apiService.postCourse(request)
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCourseById(courseId: String): Result<Course> {
        return try {
            val response = apiService.getCourseById(courseId)
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}