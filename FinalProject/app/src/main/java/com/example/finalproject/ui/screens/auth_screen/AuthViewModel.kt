package com.example.finalproject.ui.screens.auth_screen

import androidx.lifecycle.ViewModel
import com.example.finalproject.domain.usecases.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(), ContainerHost<AuthState, AuthSideEffect> {

    override val container = container<AuthState, AuthSideEffect>(AuthState())

    fun login(phoneNumber: String, password: String) = intent {
        reduce { state.copy(isLoading = true) }

        val result = loginUseCase.login(phoneNumber, password)

        result.fold(
            onSuccess = { isLoggedIn ->
                if (isLoggedIn) {
                    reduce { state.copy(isLoading = false, isLoggedIn = true) }
                    postSideEffect(AuthSideEffect.NavigateToHome)
                } else {
                    reduce { state.copy(isLoading = false, errorMessage = "Invalid credentials") }
                    postSideEffect(AuthSideEffect.ShowError("Invalid credentials"))
                }
            },
            onFailure = { error ->
                reduce { state.copy(isLoading = false, errorMessage = error.message) }
                postSideEffect(AuthSideEffect.ShowError(error.message ?: "Unknown error"))
            }
        )
    }

    fun onRegisterClicked() = intent {
        postSideEffect(AuthSideEffect.NavigateToRegister)
    }
}