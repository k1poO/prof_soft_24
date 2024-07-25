package com.example.profsoft_lesson3.di.modules

import com.example.profsoft_lesson3.data.repositories.WeatherRepository
import com.example.profsoft_lesson3.data.repositories.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepository: WeatherRepository
    ): WeatherRepositoryImpl
}