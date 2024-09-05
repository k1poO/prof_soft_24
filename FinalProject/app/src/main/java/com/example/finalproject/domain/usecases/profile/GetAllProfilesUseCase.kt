package com.example.finalproject.domain.usecases.profile

import com.example.finalproject.data.repositories.ProfileRepositoryImpl
import com.example.finalproject.domain.models.Profiles
import javax.inject.Inject

class GetAllProfilesUseCase @Inject constructor(
    private val profileRepositoryImpl: ProfileRepositoryImpl
) {

    suspend fun getAllProfiles(): Result<Profiles> {
        return profileRepositoryImpl.getAllProfiles()
    }
}