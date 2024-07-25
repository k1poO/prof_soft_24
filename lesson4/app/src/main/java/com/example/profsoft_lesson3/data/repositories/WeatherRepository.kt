package com.example.profsoft_lesson3.data.repositories

import com.example.profsoft_lesson3.data.api.ApiService
import com.example.profsoft_lesson3.domain.models.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: ApiService
) : WeatherRepositoryImpl {

    override suspend fun getWeather(): Weather {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getWeather()
                Weather(
                    city = response.name,
                    temp = convertKelvinToCelsius(response.main.temp),
                    tempFeelsLike = convertKelvinToCelsius(response.main.feelsLike),
                    weatherDescription = response.weather[0].description,
                    minTemp = convertKelvinToCelsius(response.main.tempMin),
                    maxTemp = convertKelvinToCelsius(response.main.tempMax),
                    pressure = response.main.pressure,
                    humidity = response.main.humidity,
                    visibility = convertVisibility(response.visibility),
                    windSpeed = response.wind.speed,
                    windGust = response.wind.gust,
                    windDirection = convertWindDirection(response.wind.deg)
                )
            } catch (e: Exception) {
                throw Exception("Failed to fetch weather data: ${e.message}")
            }
        }
    }

    private fun convertKelvinToCelsius(temp: Double): Double {
        return temp - 273.15
    }

    private fun convertVisibility(visibility: Int): Double {
        return visibility / 1000.0
    }

    private fun convertWindDirection(degree: Int): String {
        val directions = arrayOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
        val index = ((degree + 22.5) / 45).toInt() % 8
        return directions[index]
    }
}