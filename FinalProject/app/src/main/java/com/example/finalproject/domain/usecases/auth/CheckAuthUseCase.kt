package com.example.finalproject.domain.usecases.auth

import com.example.finalproject.data.storage.TokenProvider
import javax.inject.Inject

class CheckAuthUseCase @Inject constructor(
    private val tokenProvider: TokenProvider
) {

    fun isTokenSaved(): Boolean {
        return tokenProvider.isTokenSaved()
    }
}