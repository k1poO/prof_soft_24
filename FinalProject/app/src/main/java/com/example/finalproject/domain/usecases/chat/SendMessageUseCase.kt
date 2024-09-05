package com.example.finalproject.domain.usecases.chat

import com.example.finalproject.data.repositories.ChatRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToDataMapper
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.ui.models.ChatVO
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val chatRepositoryImpl: ChatRepositoryImpl,
    private val domainToDataMapper: DomainToDataMapper,
    private val domainToViewMapper: DomainToViewMapper
) {

    suspend fun sendMessage(message: String): Result<ChatVO> {
        val request = domainToDataMapper.textToChatRequest(message)
        return chatRepositoryImpl.sendChatMessage(request).mapCatching {
            domainToViewMapper.run { it.toView() }
        }
    }
}