package com.example.finalproject.domain.repositories

import com.example.finalproject.data.models.profile.ChangeRoleRequest
import com.example.finalproject.data.models.profile.PhoneVisibilityRequest
import com.example.finalproject.domain.models.Profile
import com.example.finalproject.domain.models.Profiles

interface ProfileRepository {

    suspend fun getProfile(): Result<Profile>

    suspend fun getProfileById(userId: String) : Result<Profile>

    suspend fun getAllProfiles() : Result<Profiles>

    suspend fun changeRole(roleRequest: ChangeRoleRequest) : Result<Profile>

    suspend fun changePhoneVisibility(phoneRequest: PhoneVisibilityRequest) : Result<Profile>
}