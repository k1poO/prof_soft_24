package com.example.finalproject.domain.repositories

import com.example.finalproject.data.models.authorization.RegisterRequest

interface RegisterRepository {

    suspend fun register(registerRequest: RegisterRequest): Result<Boolean>
}