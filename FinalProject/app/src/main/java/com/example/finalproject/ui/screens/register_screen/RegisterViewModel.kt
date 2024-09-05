package com.example.finalproject.ui.screens.register_screen

import androidx.lifecycle.ViewModel
import com.example.finalproject.domain.usecases.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel(), ContainerHost<RegisterState, RegisterSideEffect> {

    override val container = container<RegisterState, RegisterSideEffect>(RegisterState())

    fun register(
        phoneNumber: String,
        name: String,
        surname: String,
        password: String,
        avatar: String = ""
    ) = intent {
        reduce { state.copy(isLoading = true) }

        val result = registerUseCase.execute(
            phoneNumber = phoneNumber,
            name = name,
            surname = surname,
            password = password,
            avatar = avatar
        )

        result.fold(
            onSuccess = { isRegister ->
                if (isRegister) {
                    reduce { state.copy(isLoading = false, isRegister = true) }
                    postSideEffect(RegisterSideEffect.NavigateToHome)
                } else {
                    reduce { state.copy(isLoading = false, errorMessage = "Invalid credentials") }
                    postSideEffect(RegisterSideEffect.ShowError("Invalid credentials"))
                }
            },
            onFailure = { error ->
                reduce { state.copy(isLoading = false, errorMessage = error.message) }
                postSideEffect(RegisterSideEffect.ShowError(error.message ?: "Unknown error"))
            }
        )
    }

    fun onLoginClicked() = intent {
        postSideEffect(RegisterSideEffect.NavigateToAuth)
    }
}