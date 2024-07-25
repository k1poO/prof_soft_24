package com.example.profsoft_lesson3.domain.interactor

import com.example.profsoft_lesson3.data.repositories.WeatherRepositoryImpl
import com.example.profsoft_lesson3.ui.models.WeatherVO
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepositoryImpl
) : WeatherUseCaseImpl {

    override suspend fun getWeather(): WeatherVO {
        val response = weatherRepository.getWeather()
        return WeatherVO(
            city = response.city,
            temp = formatTemp(response.temp),
            tempFeelsLike = formatTemp(response.tempFeelsLike),
            weatherDesc = response.weatherDescription,
            minTemp = formatTemp(response.minTemp),
            maxTemp = formatTemp(response.maxTemp),
            pressure = response.pressure,
            humidity = response.humidity,
            visibility = response.visibility,
            windSpeed = response.windSpeed,
            windGust = response.windGust,
            windDirection = response.windDirection
        )
    }

    private fun formatTemp(temp: Double): String {
        return "${temp.toInt()}°C"
    }
}
