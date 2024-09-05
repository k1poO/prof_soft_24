package com.example.finalproject.ui.screens.chat_screen

import androidx.lifecycle.ViewModel
import com.example.finalproject.domain.usecases.chat.GetChatUseCase
import com.example.finalproject.domain.usecases.chat.SendMessageUseCase
import com.example.finalproject.domain.usecases.profile.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatUseCase: GetChatUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel(), ContainerHost<ChatState, ChatSideEffect> {

    override val container = container<ChatState, ChatSideEffect>(ChatState())

    init {
        getChat()
        getCurrentUserId()
    }

    fun onRefreshClicked() {
        getChat()
        getCurrentUserId()
    }

    fun onSendClicked(message: String) {
        if (message.isNotBlank()) {
            sendMessage(message)
        }
    }

    private fun getChat() = intent {
        reduce { state.copy(isLoading = true) }

        val result = getChatUseCase.getChat()

        result.onSuccess { chat ->
            reduce { state.copy(isLoading = false, chat = chat) }
        }.onFailure { error ->
            postSideEffect(ChatSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoading = false) }
        }
    }

    // Загрузка профиля
    fun getCurrentUserId() = intent {
        reduce { state.copy(isLoading = true) }

        val profileResult = getProfileUseCase.getProfile()

        profileResult.onSuccess { profile ->
            reduce { state.copy(isLoading = false, currentUserId = profile.id) }
        }.onFailure { error ->
            postSideEffect(ChatSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoading = false) }
        }
    }


    private fun sendMessage(message: String) = intent {
        reduce { state.copy(isLoading = true) }

        val result = sendMessageUseCase.sendMessage(message = message)

        result.onSuccess { chat ->
            reduce { state.copy(isLoading = false, chat = chat) }
        }.onFailure { error ->
            postSideEffect(ChatSideEffect.ShowError(error.message ?: "Unknown Error"))
            reduce { state.copy(isLoading = false) }
        }
    }
}
