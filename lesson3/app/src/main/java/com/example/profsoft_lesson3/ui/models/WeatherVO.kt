package com.example.profsoft_lesson3.ui.models

data class WeatherVO(
    val city: String,
    val temp: String,
    val tempFeelsLike: String,
    val weatherDesc: String,
    val minTemp: String,
    val maxTemp: String,
    val pressure: Int,
    val humidity: Int,
    val visibility: Double,
    val windSpeed: Double,
    val windGust: Double,
    val windDirection: String
)