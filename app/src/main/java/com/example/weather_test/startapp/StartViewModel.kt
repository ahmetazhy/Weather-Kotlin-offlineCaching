package com.example.weather_test.startapp

import android.app.Application
import androidx.lifecycle.*
import com.example.weather_test.database.getDatabase
import com.example.weather_test.network.Forecast
import com.example.weather_test.repository.WeatherPropertyRepository
import kotlinx.coroutines.launch

class StartViewModel(application: Application) : AndroidViewModel(application) {

    private val databaseWork = getDatabase(application)
    private val weatherPropertyRepository = WeatherPropertyRepository(databaseWork)

    private val database = getDatabase(application)
    private val weatherRepository = WeatherPropertyRepository(database)

    init {
        viewModelScope.launch {
            weatherRepository.refreshWeatherProperty()
        }
    }

    private val _navigateToSelectedWeatherProperty= MutableLiveData<Forecast>()

    val navigateToSelectedProperty: MutableLiveData<Forecast>
        get() = _navigateToSelectedWeatherProperty

    val properties=weatherPropertyRepository.weatherPropertyData

    fun displayPropertyDetails(forecast: Forecast) {
        _navigateToSelectedWeatherProperty.value = forecast
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedWeatherProperty.value = null
    }

}

