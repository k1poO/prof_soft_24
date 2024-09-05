package com.example.finalproject.domain.usecases.profile

import com.example.finalproject.data.repositories.ProfileRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.ui.models.ProfileVO
import javax.inject.Inject

class GetProfileByIdUseCase @Inject constructor(
    private val profileRepositoryImpl: ProfileRepositoryImpl,
    private val domainToViewMapper: DomainToViewMapper
) {

    suspend fun getProfileById(userId: String): Result<ProfileVO> {
        return profileRepositoryImpl.getProfileById(userId).mapCatching {
            domainToViewMapper.run { it.toView() }
        }
    }
}
