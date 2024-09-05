package com.example.finalproject.data.api

import com.example.finalproject.data.models.authorization.AuthRequest
import com.example.finalproject.data.models.authorization.AuthResponseDTO
import com.example.finalproject.data.models.authorization.RegisterRequest
import com.example.finalproject.data.models.chat.ChatRequest
import com.example.finalproject.data.models.chat.ChatResponse
import com.example.finalproject.data.models.courses.AllCourseResponse
import com.example.finalproject.data.models.courses.CourseRequest
import com.example.finalproject.data.models.courses.CourseResponse
import com.example.finalproject.data.models.notes.CommentNoteRequest
import com.example.finalproject.data.models.notes.NotesResponse
import com.example.finalproject.data.models.notes.PostNoteRequest
import com.example.finalproject.data.models.notes.SingleNoteResponse
import com.example.finalproject.data.models.profile.ChangeRoleRequest
import com.example.finalproject.data.models.profile.PhoneVisibilityRequest
import com.example.finalproject.data.models.profile.ProfilesResponse
import com.example.finalproject.data.models.profile.SingleProfileDTO
import com.example.finalproject.data.models.profile.SingleProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //Authorization
    @POST("auth")
    suspend fun authenticate(@Body request: AuthRequest): AuthResponseDTO

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): AuthResponseDTO

    //Courses
    @GET("courses")
    suspend fun getAllCourses(): AllCourseResponse

    @POST("courses")
    suspend fun postCourse(@Body request: CourseRequest): CourseResponse

    @GET("courses/{courseId}")
    suspend fun getCourseById(
        @Path(COURSE_ID) courseId: String
    ): CourseResponse

    //Notes
    @GET("community_notes")
    suspend fun getAllNotes(): NotesResponse

    @POST("community_notes")
    suspend fun postNote(@Body request: PostNoteRequest): SingleNoteResponse

    @GET("community_notes/{noteId}")
    suspend fun getNoteById(
        @Path(NOTE_ID) noteId: String
    ): SingleNoteResponse

    @POST("community_notes/comment/{noteId}")
    suspend fun commentNote(
        @Path(NOTE_ID) noteId: String,
        @Body request: CommentNoteRequest
    ): SingleNoteResponse

    //Chat
    @GET("chat")
    suspend fun getChat(): ChatResponse

    @POST("chat")
    suspend fun sendChatMessage(@Body request: ChatRequest): ChatResponse

    //Profile
    @GET("profile")
    suspend fun getProfile(): SingleProfileResponse

    @GET("profile/{userId}")
    suspend fun getProfileById(
        @Path(USER_ID) userId: String
    ): SingleProfileDTO

    @GET("profile/all")
    suspend fun getAllProfiles(): ProfilesResponse

    @POST("profile/role")
    suspend fun changeRole(@Body request: ChangeRoleRequest): SingleProfileResponse

    @POST("profile/phone_visibility")
    suspend fun changePhoneVisibility(@Body request: PhoneVisibilityRequest): SingleProfileResponse

    companion object {

        private const val COURSE_ID = "courseId"
        private const val NOTE_ID = "noteId"
        private const val USER_ID = "userId"
    }
}