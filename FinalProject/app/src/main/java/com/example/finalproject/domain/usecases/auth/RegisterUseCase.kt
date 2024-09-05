package com.example.finalproject.domain.usecases.auth

import com.example.finalproject.data.repositories.RegisterRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToDataMapper
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepositoryImpl: RegisterRepositoryImpl,
    private val domainToDataMapper: DomainToDataMapper
) {

    suspend fun execute(
        phoneNumber: String,
        password: String,
        name: String,
        surname: String,
        avatar: String
    ): Result<Boolean> {
        val request = domainToDataMapper.registerRequestToData(
            phone = phoneNumber,
            password = password,
            name = name,
            surname = surname,
            avatar = avatar
        )
        return registerRepositoryImpl.register(request)
    }
}