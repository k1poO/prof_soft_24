package com.example.profsoft_lesson3.ui.main_screen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.profsoft_lesson3.domain.interactor.WeatherUseCaseImpl
import com.example.profsoft_lesson3.ui.models.WeatherVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    application: Application,
    private val weatherUseCase: WeatherUseCaseImpl
) : AndroidViewModel(application) {

    private val _weather = MutableLiveData<WeatherVO>()
    val weather: LiveData<WeatherVO> get() = _weather

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getWeatherInfo()
    }

    private fun getWeatherInfo() {
        viewModelScope.launch {
            try {
                val weatherData = weatherUseCase.getWeather()
                _weather.value = weatherData
                Log.d("WeatherViewModel", "getWeatherInfo: ${_weather.value}")
            } catch (e: Exception) {
                _error.value = e.message
                Log.d("WeatherViewModel", "getWeatherInfo: ${e.message}")
            }
        }
    }
}
