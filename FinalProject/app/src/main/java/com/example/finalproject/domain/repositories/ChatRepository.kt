package com.example.finalproject.domain.repositories

import com.example.finalproject.data.models.chat.ChatRequest
import com.example.finalproject.data.models.chat.ChatResponse
import com.example.finalproject.domain.models.Chat

interface ChatRepository {

    suspend fun getChat(): Result<Chat>

    suspend fun sendChatMessage(message: ChatRequest): Result<Chat>

}