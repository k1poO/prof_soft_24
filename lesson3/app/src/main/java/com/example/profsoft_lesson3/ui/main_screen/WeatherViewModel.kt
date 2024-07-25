package com.example.profsoft_lesson3.ui.main_screen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.profsoft_lesson3.domain.interactor.WeatherUseCase
import com.example.profsoft_lesson3.data.repositories.WeatherRepository
import com.example.profsoft_lesson3.domain.interactor.WeatherUseCaseImpl
import com.example.profsoft_lesson3.ui.models.WeatherVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val _weather = MutableLiveData<WeatherVO>()
    val weather: LiveData<WeatherVO> get() = _weather

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val weatherUseCase: WeatherUseCaseImpl =
        WeatherUseCase(weatherRepository = WeatherRepository())

    private val viewModelJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getWeatherInfo()
    }

    private fun getWeatherInfo() {
        coroutineScope.launch {
            try {
                val weatherData = withContext(Dispatchers.IO) {
                    weatherUseCase.getWeather()
                }
                _weather.value = weatherData
                Log.d("Lips", "getWeatherInfo: ${_weather.value.toString()}")
            } catch (e: Exception) {
                _error.value = e.message
                Log.d("Lips", "getWeatherInfo: ${e.message.toString()}")
            }
        }
    }
}