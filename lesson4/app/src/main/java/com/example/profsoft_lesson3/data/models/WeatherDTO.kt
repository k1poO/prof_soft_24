package com.example.profsoft_lesson3.data.models

data class WeatherDTO(
    val main: Main,
    val name: String,
    val visibility: Int,
    val weather: List<WeatherDesc>,
    val wind: Wind
)