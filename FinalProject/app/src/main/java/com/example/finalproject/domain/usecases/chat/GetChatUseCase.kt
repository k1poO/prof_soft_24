package com.example.finalproject.domain.usecases.chat

import com.example.finalproject.data.repositories.ChatRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.domain.models.Chat
import com.example.finalproject.ui.models.ChatVO
import javax.inject.Inject

class GetChatUseCase @Inject constructor(
    private val chatRepositoryImpl: ChatRepositoryImpl,
    private val domainToViewMapper: DomainToViewMapper
) {

    suspend fun getChat(): Result<ChatVO> {
        return chatRepositoryImpl.getChat().mapCatching {
            domainToViewMapper.run { it.toView() }
        }
    }
}

