package com.example.finalproject.di.modules

import com.example.finalproject.data.repositories.AuthRepositoryImpl
import com.example.finalproject.data.repositories.ChatRepositoryImpl
import com.example.finalproject.data.repositories.CourseRepositoryImpl
import com.example.finalproject.data.repositories.NoteRepositoryImpl
import com.example.finalproject.data.repositories.ProfileRepositoryImpl
import com.example.finalproject.data.repositories.RegisterRepositoryImpl
import com.example.finalproject.data.storage.TokenProvider
import com.example.finalproject.domain.mapper.DomainToDataMapper
import com.example.finalproject.domain.mapper.DomainToViewMapper
import com.example.finalproject.domain.security.PasswordHasher
import com.example.finalproject.domain.usecases.auth.CheckAuthUseCase
import com.example.finalproject.domain.usecases.auth.LoginUseCase
import com.example.finalproject.domain.usecases.auth.LogoutUseCase
import com.example.finalproject.domain.usecases.auth.RegisterUseCase
import com.example.finalproject.domain.usecases.chat.GetChatUseCase
import com.example.finalproject.domain.usecases.chat.SendMessageUseCase
import com.example.finalproject.domain.usecases.course.GetAllCoursesUseCase
import com.example.finalproject.domain.usecases.course.GetCourseByIdUseCase
import com.example.finalproject.domain.usecases.course.GetLastCoursesUseCase
import com.example.finalproject.domain.usecases.note.AddCommentUseCase
import com.example.finalproject.domain.usecases.note.GetAllComNotesUseCase
import com.example.finalproject.domain.usecases.note.GetAllLocalNotesUseCase
import com.example.finalproject.domain.usecases.note.GetComNoteByIdUseCase
import com.example.finalproject.domain.usecases.note.GetFavouriteNotesUseCase
import com.example.finalproject.domain.usecases.note.GetLastComNoteUseCase
import com.example.finalproject.domain.usecases.note.GetLastLocalNoteUseCase
import com.example.finalproject.domain.usecases.note.GetLocalNoteByIdUseCase
import com.example.finalproject.domain.usecases.note.GetLocalNoteUseCase
import com.example.finalproject.domain.usecases.note.SaveLocalNoteUseCase
import com.example.finalproject.domain.usecases.profile.GetAllProfilesUseCase
import com.example.finalproject.domain.usecases.profile.GetProfileByIdUseCase
import com.example.finalproject.domain.usecases.profile.GetProfileUseCase
import com.example.finalproject.domain.usecases.profile.PhoneVisibilityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideDomainToDataMapper(
        hasher: PasswordHasher
    ): DomainToDataMapper {
        return DomainToDataMapper(hasher)
    }

    @Provides
    fun provideDomainToViewMapper(): DomainToViewMapper {
        return DomainToViewMapper()
    }

    @Provides
    fun provideLoginUseCase(
        authRepositoryImpl: AuthRepositoryImpl,
        domainToDataMapper: DomainToDataMapper
    ): LoginUseCase {
        return LoginUseCase(authRepositoryImpl, domainToDataMapper)
    }

    @Provides
    fun provideCheckAuthUseCase(
        tokenProvider: TokenProvider,
    ): CheckAuthUseCase {
        return CheckAuthUseCase(tokenProvider)
    }

    @Provides
    fun provideLogoutUseCase(
        authRepositoryImpl: AuthRepositoryImpl
    ): LogoutUseCase {
        return LogoutUseCase(authRepositoryImpl)
    }

    @Provides
    fun provideRegisterUseCase(
        registerRepositoryImpl: RegisterRepositoryImpl,
        domainToDataMapper: DomainToDataMapper
    ): RegisterUseCase {
        return RegisterUseCase(registerRepositoryImpl, domainToDataMapper)
    }

    @Provides
    fun provideGetAllProfilesUseCase(
        profileRepositoryImpl: ProfileRepositoryImpl
    ): GetAllProfilesUseCase {
        return GetAllProfilesUseCase(profileRepositoryImpl)
    }

    @Provides
    fun provideGetProfileByIdUseCase(
        profileRepositoryImpl: ProfileRepositoryImpl,
        domainToViewMapper: DomainToViewMapper
    ): GetProfileByIdUseCase {
        return GetProfileByIdUseCase(profileRepositoryImpl, domainToViewMapper)
    }

    @Provides
    fun provideGetProfileUseCase(
        profileRepositoryImpl: ProfileRepositoryImpl,
        domainToViewMapper: DomainToViewMapper
    ): GetProfileUseCase {
        return GetProfileUseCase(profileRepositoryImpl, domainToViewMapper)
    }

    @Provides
    fun providePhoneVisibilityUseCase(
        profileRepositoryImpl: ProfileRepositoryImpl,
        domainToDataMapper: DomainToDataMapper,
        domainToViewMapper: DomainToViewMapper
    ): PhoneVisibilityUseCase {
        return PhoneVisibilityUseCase(profileRepositoryImpl, domainToDataMapper, domainToViewMapper)
    }

    @Provides
    fun provideGetAllCoursesUseCase(
        courseRepositoryImpl: CourseRepositoryImpl
    ): GetAllCoursesUseCase {
        return GetAllCoursesUseCase(courseRepositoryImpl)
    }

    @Provides
    fun provideGetCourseByIdUseCase(
        courseRepositoryImpl: CourseRepositoryImpl
    ): GetCourseByIdUseCase {
        return GetCourseByIdUseCase(courseRepositoryImpl)
    }

    @Provides
    fun provideGetLastCoursesUseCase(
        courseRepositoryImpl: CourseRepositoryImpl
    ): GetLastCoursesUseCase {
        return GetLastCoursesUseCase(courseRepositoryImpl)
    }

    @Provides
    fun provideGetChatUseCase(
        chatRepositoryImpl: ChatRepositoryImpl,
        domainToViewMapper: DomainToViewMapper
    ): GetChatUseCase {
        return GetChatUseCase(chatRepositoryImpl, domainToViewMapper)
    }

    @Provides
    fun provideSendMessageUseCase(
        chatRepositoryImpl: ChatRepositoryImpl,
        domainToDataMapper: DomainToDataMapper,
        domainToViewMapper: DomainToViewMapper
    ): SendMessageUseCase {
        return SendMessageUseCase(chatRepositoryImpl, domainToDataMapper, domainToViewMapper)
    }

    @Provides
    fun provideAddCommentUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToDataMapper: DomainToDataMapper,
        domainToViewMapper: DomainToViewMapper
    ): AddCommentUseCase {
        return AddCommentUseCase(noteRepositoryImpl, domainToDataMapper, domainToViewMapper)
    }

    @Provides
    fun provideGetAllComNotesUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToViewMapper: DomainToViewMapper
    ): GetAllComNotesUseCase {
        return GetAllComNotesUseCase(noteRepositoryImpl, domainToViewMapper)
    }

    @Provides
    fun provideGetAllLocalNotesUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToViewMapper: DomainToViewMapper
    ): GetAllLocalNotesUseCase {
        return GetAllLocalNotesUseCase(noteRepositoryImpl, domainToViewMapper)
    }

    @Provides
    fun provideGetComNoteByIdUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToViewMapper: DomainToViewMapper
    ): GetComNoteByIdUseCase {
        return GetComNoteByIdUseCase(noteRepositoryImpl, domainToViewMapper)
    }

    @Provides
    fun provideGetFavouriteNotesUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToViewMapper: DomainToViewMapper
    ): GetFavouriteNotesUseCase {
        return GetFavouriteNotesUseCase(noteRepositoryImpl, domainToViewMapper)
    }

    @Provides
    fun provideGetLastComNoteUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToViewMapper: DomainToViewMapper
    ): GetLastComNoteUseCase {
        return GetLastComNoteUseCase(noteRepositoryImpl, domainToViewMapper)
    }

    @Provides
    fun provideGetLastLocalNoteUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToViewMapper: DomainToViewMapper
    ): GetLastLocalNoteUseCase {
        return GetLastLocalNoteUseCase(noteRepositoryImpl, domainToViewMapper)
    }

    @Provides
    fun provideGetLocalNoteByIdUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToViewMapper: DomainToViewMapper
    ): GetLocalNoteByIdUseCase {
        return GetLocalNoteByIdUseCase(noteRepositoryImpl, domainToViewMapper)
    }

    @Provides
    fun provideGetLocalNoteUseCase(
        noteRepositoryImpl: NoteRepositoryImpl
    ): GetLocalNoteUseCase {
        return GetLocalNoteUseCase(noteRepositoryImpl)
    }

    @Provides
    fun provideSaveLocalNoteUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToDataMapper: DomainToDataMapper
    ): SaveLocalNoteUseCase {
        return SaveLocalNoteUseCase(noteRepositoryImpl, domainToDataMapper)
    }
}