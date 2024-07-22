package com.example.profsoft_lesson3.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    private const val READ_TIMEOUT_IN_SECONDS = 30L
    private const val CONNECTION_TIMEOUT_IN_SECONDS = 30L
    private const val DEV_BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val moshiBuilder = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
        .client(okHttpClient)
        .baseUrl(DEV_BASE_URL)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}