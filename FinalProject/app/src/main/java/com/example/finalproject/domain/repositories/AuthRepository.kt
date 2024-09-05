package com.example.finalproject.domain.repositories

import com.example.finalproject.data.models.authorization.AuthRequest

interface AuthRepository {

    suspend fun login(authRequest: AuthRequest): Result<Boolean>

    fun logout()
}