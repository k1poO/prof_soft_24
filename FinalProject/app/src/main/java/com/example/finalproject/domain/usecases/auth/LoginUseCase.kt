package com.example.finalproject.domain.usecases.auth

import com.example.finalproject.data.repositories.AuthRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToDataMapper
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl,
    private val domainToDataMapper: DomainToDataMapper
) {

    suspend fun login(phoneNumber: String, password: String): Result<Boolean> {
        val request = domainToDataMapper.authRequestToData(
            phone = phoneNumber,
            password = password
        )
        return authRepositoryImpl.login(request)
    }
}