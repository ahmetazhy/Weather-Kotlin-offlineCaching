package com.example.weather_test.startapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.weather_test.network.Forecast
import com.example.weather_test.network.WeatherApi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class WeatherApiStatus { LOADING, ERROR, DONE }

class StartViewModel() : ViewModel() {

    private val _status = MutableLiveData<WeatherApiStatus>()
    val status: LiveData<WeatherApiStatus>
        get() = _status



    private val _properties = MutableLiveData<List<Forecast>>()
    val properties: LiveData<List<Forecast>>
        get() = _properties


    private val _navigateToSelectedWeatherProperty= MutableLiveData<Forecast>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProperty: MutableLiveData<Forecast>
        get() = _navigateToSelectedWeatherProperty


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        getTodayIsForcast()

    }

    private fun getTodayIsForcast() {
        coroutineScope.launch {
            var getPropertiesDeferred = WeatherApi.retrofitService.getProperties()
            _status.value = WeatherApiStatus.LOADING
            try {
                var resultapi = getPropertiesDeferred.await()
                _status.value = WeatherApiStatus.DONE
                    _properties.value = resultapi.list

            } catch (e: Exception) {
                _status.value = WeatherApiStatus.ERROR
            }
        }
    }

//    private val database = getDatabase(context)
//    private val weatherPropertyRepository = WeatherPropertyRepository(database)
//
//    init {
//        viewModelScope.launch {
//            weatherPropertyRepository.refreshWeatherProperty()
//        }
//    }
//
//    val properties = weatherPropertyRepository.weatherPropertyData
//

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(forecast: Forecast) {
        _navigateToSelectedWeatherProperty.value = forecast
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedWeatherProperty.value = null
    }

}

