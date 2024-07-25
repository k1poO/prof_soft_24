package com.example.profsoft_lesson3.di.modules

import com.example.profsoft_lesson3.domain.interactor.WeatherUseCase
import com.example.profsoft_lesson3.domain.interactor.WeatherUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindWeatherUseCase(
        weatherUseCase: WeatherUseCase
    ): WeatherUseCaseImpl
}