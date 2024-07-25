package com.example.profsoft_lesson3.data.api

import com.example.profsoft_lesson3.data.models.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query(QUERY_PARAM_CITY) cityName: String = CITY,
        @Query(QUERY_PARAM_API_ID) id: String = API_ID
    ): WeatherDTO

    companion object {

        private const val QUERY_PARAM_CITY = "q"
        private const val QUERY_PARAM_API_ID = "appid"

        private const val API_ID = "aa50c9b8447c4de904a7474de259a3ad"
        private const val CITY = "saratov"
    }
}