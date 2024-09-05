package com.example.finalproject.data.repositories

import android.util.Log
import com.example.finalproject.data.api.ApiService
import com.example.finalproject.data.models.authorization.RegisterRequest
import com.example.finalproject.data.storage.TokenProvider
import com.example.finalproject.domain.repositories.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val tokenProvider: TokenProvider
) : RegisterRepository {

    override suspend fun register(registerRequest: RegisterRequest): Result<Boolean> {

        return try {
            val response = apiService.register(registerRequest)
            val token = response.data.token
            tokenProvider.saveToken(token)
            Log.d("RegisterRepositoryImpl", "Success registration")
            Result.success(true)
        } catch (e: Exception) {
            Log.d("RegisterRepositoryImpl", "Error: ${e.message}")
            Result.failure(e)
        }
    }
}
