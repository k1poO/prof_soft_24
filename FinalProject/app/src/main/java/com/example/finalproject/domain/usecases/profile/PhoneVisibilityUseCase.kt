package com.example.finalproject.domain.usecases.profile

import com.example.finalproject.data.repositories.ProfileRepositoryImpl
import com.example.finalproject.domain.mapper.DomainToDataMapper
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.domain.models.Profile
import com.example.finalproject.ui.models.ProfileVO
import javax.inject.Inject

class PhoneVisibilityUseCase @Inject constructor(
    private val profileRepositoryImpl: ProfileRepositoryImpl,
    private val domainToDataMapper: DomainToDataMapper,
    private val domainToViewMapper: DomainToViewMapper
) {

    suspend fun changePhoneVisibility(isVisible: Boolean): Result<ProfileVO> {
        val request = domainToDataMapper.changePhoneVisibilityToData(isVisible = isVisible)
        return profileRepositoryImpl.changePhoneVisibility(request).mapCatching {
            domainToViewMapper.run { it.toView() }
        }
    }
}