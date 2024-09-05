package com.example.finalproject.data.repositories

import com.example.finalproject.data.api.ApiService
import com.example.finalproject.data.mapper.DataToDomainMapper
import com.example.finalproject.data.models.chat.ChatRequest
import com.example.finalproject.domain.models.Chat
import com.example.finalproject.domain.repositories.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dataToDomainMapper: DataToDomainMapper
) : ChatRepository {

    override suspend fun getChat(): Result<Chat> {
        return try {
            val response = apiService.getChat()
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun sendChatMessage(message: ChatRequest): Result<Chat> {
        return try {
            val response = apiService.sendChatMessage(message)
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}