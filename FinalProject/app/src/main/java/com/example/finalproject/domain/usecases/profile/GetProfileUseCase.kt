package com.example.finalproject.domain.usecases.profile

import com.example.finalproject.data.repositories.ProfileRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToDataMapper
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.domain.models.Profile
import com.example.finalproject.ui.models.ProfileVO
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepositoryImpl: ProfileRepositoryImpl,
    private val domainToViewMapper: DomainToViewMapper
) {

    suspend fun getProfile(): Result<ProfileVO> {
        return profileRepositoryImpl.getProfile().mapCatching { profile ->
            domainToViewMapper.run { profile.toView() }
        }
    }
}