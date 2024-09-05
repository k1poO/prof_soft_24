package com.example.finalproject.data.repositories

import android.util.Log
import com.example.finalproject.data.api.ApiService
import com.example.finalproject.data.models.authorization.AuthRequest
import com.example.finalproject.data.storage.TokenProvider
import com.example.finalproject.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val tokenProvider: TokenProvider
) : AuthRepository {

    override suspend fun login(authRequest: AuthRequest): Result<Boolean> {
        return try {
            val response = apiService.authenticate(authRequest)
            val token = response.data.token
            tokenProvider.saveToken(token)
            Log.d("AuthRepositoryImpl", "login: $token")
            Result.success(true)
        } catch (e: Exception) {
            Log.d("AuthRepositoryImpl", "login: ${e.message}")
            Result.failure(e)
        }
    }

    override fun logout() {
        tokenProvider.clearToken()
    }
}