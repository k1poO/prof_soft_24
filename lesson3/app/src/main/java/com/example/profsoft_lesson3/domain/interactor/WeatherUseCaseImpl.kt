package com.example.profsoft_lesson3.domain.interactor

import com.example.profsoft_lesson3.ui.models.WeatherVO

interface WeatherUseCaseImpl {

    suspend fun getWeather(): WeatherVO
}