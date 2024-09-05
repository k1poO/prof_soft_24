package com.example.finalproject.ui.screens.splash_screen

import androidx.lifecycle.ViewModel
import com.example.finalproject.domain.usecases.auth.CheckAuthUseCase
import com.example.finalproject.domain.usecases.note.GetLastComNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val checkAuthUseCase: CheckAuthUseCase,
    private val getLastComNoteUseCase: GetLastComNoteUseCase
) : ContainerHost<SplashState, SplashSideEffect>, ViewModel() {

    override val container = container<SplashState, SplashSideEffect>(SplashState())

    fun checkUserLoggedIn() = intent {
        val isUserLoggedIn = checkAuthUseCase.isTokenSaved()

        if (isUserLoggedIn) {
            val result = getLastComNoteUseCase.getLastNote()

            result.onFailure { exception ->
                // Проверяем, содержит ли сообщение ошибки "Unauthorized"
                if (exception.message?.contains("Unauthorized") == true) {
                    // Навигируем на экран авторизации
                    postSideEffect(SplashSideEffect.NavigateToAuth)
                    return@intent
                }
            }

            // Если ошибки не было или она не связана с "Unauthorized", переходим на главный экран
            postSideEffect(SplashSideEffect.NavigateToMain)
        } else {
            // Если пользователь не залогинен, переходим на экран авторизации
            postSideEffect(SplashSideEffect.NavigateToAuth)
        }

        // Обновляем состояние, указывая, что пользователь залогинен или нет
        reduce { state.copy(isUserLoggedIn = isUserLoggedIn) }
    }

}
