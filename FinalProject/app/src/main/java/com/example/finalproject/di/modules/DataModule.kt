package com.example.finalproject.di.modules

import android.content.Context
import com.example.finalproject.data.api.ApiService
import com.example.finalproject.data.mapper.DataToDomainMapper
import com.example.finalproject.data.repositories.AuthRepositoryImpl
import com.example.finalproject.data.repositories.ChatRepositoryImpl
import com.example.finalproject.data.repositories.CourseRepositoryImpl
import com.example.finalproject.data.repositories.NoteRepositoryImpl
import com.example.finalproject.data.repositories.ProfileRepositoryImpl
import com.example.finalproject.data.repositories.RegisterRepositoryImpl
import com.example.finalproject.data.security.Md5PasswordHasher
import com.example.finalproject.data.storage.NoteDao
import com.example.finalproject.data.storage.SharedPreferencesManager
import com.example.finalproject.data.storage.TokenProvider
import com.example.finalproject.domain.repositories.AuthRepository
import com.example.finalproject.domain.repositories.CourseRepository
import com.example.finalproject.domain.repositories.NoteRepository
import com.example.finalproject.domain.repositories.ProfileRepository
import com.example.finalproject.domain.repositories.RegisterRepository
import com.example.finalproject.domain.security.PasswordHasher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDataToDomainMapper(
    ): DataToDomainMapper {
        return DataToDomainMapper()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: ApiService,
        tokenProvider: TokenProvider
    ): AuthRepository {
        return AuthRepositoryImpl(apiService, tokenProvider)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        apiService: ApiService,
        tokenProvider: TokenProvider,
        dataToDomainMapper: DataToDomainMapper
    ): ProfileRepository {
        return ProfileRepositoryImpl(apiService, tokenProvider, dataToDomainMapper)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(
        apiService: ApiService,
        tokenProvider: TokenProvider
    ): RegisterRepository {
        return RegisterRepositoryImpl(apiService, tokenProvider)
    }

    @Provides
    @Singleton
    fun provideCourseRepository(
        apiService: ApiService,
        dataToDomainMapper: DataToDomainMapper
    ): CourseRepository {
        return CourseRepositoryImpl(apiService, dataToDomainMapper)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        apiService: ApiService,
        dataToDomainMapper: DataToDomainMapper,
        noteDao: NoteDao
    ): NoteRepository {
        return NoteRepositoryImpl(apiService, dataToDomainMapper, noteDao)
    }

    @Provides
    @Singleton
    fun provideChatRepository(
        apiService: ApiService,
        dataToDomainMapper: DataToDomainMapper
    ): ChatRepositoryImpl {
        return ChatRepositoryImpl(apiService, dataToDomainMapper)
    }

    @Provides
    @Singleton
    fun providePasswordHasher(): PasswordHasher {
        return Md5PasswordHasher()
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(@ApplicationContext context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

    @Provides
    @Singleton
    fun provideTokenProvider(sharedPreferencesManager: SharedPreferencesManager): TokenProvider {
        return TokenProvider(sharedPreferencesManager)
    }
}