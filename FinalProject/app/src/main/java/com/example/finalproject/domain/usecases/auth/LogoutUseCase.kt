package com.example.finalproject.domain.usecases.auth

import com.example.finalproject.data.repositories.AuthRepositoryImpl
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl
) {

    fun logout() {
        authRepositoryImpl.logout()
    }
}