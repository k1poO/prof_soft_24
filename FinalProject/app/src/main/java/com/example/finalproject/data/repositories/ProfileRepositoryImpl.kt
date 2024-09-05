package com.example.finalproject.data.repositories

import android.util.Log
import com.example.finalproject.data.api.ApiService
import com.example.finalproject.data.mapper.DataToDomainMapper
import com.example.finalproject.data.models.profile.ChangeRoleRequest
import com.example.finalproject.data.models.profile.PhoneVisibilityRequest
import com.example.finalproject.data.storage.TokenProvider
import com.example.finalproject.domain.models.Profile
import com.example.finalproject.domain.models.Profiles
import com.example.finalproject.domain.repositories.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    tokenProvider: TokenProvider,
    private val dataToDomainMapper: DataToDomainMapper
) : ProfileRepository {

    override suspend fun getProfile(): Result<Profile> {
        return try {
            val response = apiService.getProfile()
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getProfileById(userId: String): Result<Profile> {
        return try {
            val response = apiService.getProfileById(userId)
            val result = dataToDomainMapper.run { response.toDomain() }
            Log.d("TAG", "getProfileById: $result")
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllProfiles(): Result<Profiles> {
        return try {
            val response = apiService.getAllProfiles()
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun changeRole(roleRequest: ChangeRoleRequest): Result<Profile> {
        return try {
            val response = apiService.changeRole(roleRequest)
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun changePhoneVisibility(
        phoneRequest: PhoneVisibilityRequest
    ): Result<Profile> {
        return try {
            val response = apiService.changePhoneVisibility(phoneRequest)
            val result = dataToDomainMapper.run { response.toDomain() }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
