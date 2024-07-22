package com.example.profsoft_lesson3.data.repositories

import com.example.profsoft_lesson3.domain.models.Weather

interface WeatherRepositoryImpl {

    suspend fun getWeather(): Weather
}