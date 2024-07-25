package com.example.profsoft_lesson3.ui.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.profsoft_lesson3.R
import com.example.profsoft_lesson3.databinding.FragmentWeatherBinding
import com.example.profsoft_lesson3.ui.models.WeatherVO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels()

    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding
        get() = _binding ?: throw RuntimeException("FragmentWeatherBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.weather.observe(viewLifecycleOwner) { weatherVO ->
            updateUI(weatherVO)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let { error ->
                showError(error)
            }
        }
    }

    private fun updateUI(weather: WeatherVO) {
        with(binding) {
            textViewCity.text = weather.city
            textViewCurrentTemp.text = weather.temp
            textViewFeelsTemp.text = getString(
                R.string.feels_like_temp,
                weather.tempFeelsLike,
                weather.weatherDesc
            )
            textViewMinMaxTemp.text =
                getString(R.string.min_max_temp, weather.minTemp, weather.maxTemp)
            textViewPressure.text =
                getString(R.string.weather_pressure, weather.pressure.toString())
            textViewHumidity.text = getString(R.string.weather_humidity, weather.humidity)
            textViewVisibility.text = getString(R.string.weather_visibility, weather.visibility)
            textViewWindSpeed.text =
                getString(R.string.weather_wind_speed, weather.windSpeed.toString())
            textViewWindGust.text =
                getString(R.string.weather_wind_gust, weather.windGust.toString())
            textViewWindDirection.text =
                getString(R.string.weather_wind_direction, weather.windDirection)
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
