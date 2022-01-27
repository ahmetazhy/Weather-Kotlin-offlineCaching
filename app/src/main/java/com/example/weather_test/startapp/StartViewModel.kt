package com.example.weather_test.startapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.weather_test.database.getDatabase


import com.example.weather_test.network.Forecast
import com.example.weather_test.network.WeatherApi
import com.example.weather_test.repository.WeatherPropertyRepository


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


enum class WeatherApiStatus { LOADING, ERROR, DONE }

class StartViewModel(application: Application) : AndroidViewModel(application) {

    private val _status = MutableLiveData<WeatherApiStatus>()
    val status: LiveData<WeatherApiStatus>
        get() = _status



    private val databaseWork = getDatabase(application)
    private val weatherPropertyRepository = WeatherPropertyRepository(databaseWork)
//
    private val _navigateToSelectedWeatherProperty= MutableLiveData<Forecast>()
////
////    // The external immutable LiveData for the navigation property
    val navigateToSelectedProperty: MutableLiveData<Forecast>
        get() = _navigateToSelectedWeatherProperty
//

    private val database = getDatabase(application)
    private val weatherRepository = WeatherPropertyRepository(database)

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        viewModelScope.launch {
            weatherRepository.refreshWeatherProperty()
        }
    }

    val properties=weatherPropertyRepository.weatherPropertyData

//    private val _properties = MutableLiveData<List<Forecast>>()
//    val properties: LiveData<List<Forecast>>
//        get() = _properties
//
//
//
//
//    private var viewModelJob = Job()
//    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//
//
//    init {
//        getTodayIsForcast()
//    }
//
//    private fun getTodayIsForcast() {
//        coroutineScope.launch {
//            var getPropertiesDeferred = WeatherApi.retrofitService.getProperties()
//            _status.value = WeatherApiStatus.LOADING
//            try {
//                var resultapi = getPropertiesDeferred.await()
//
//                _status.value = WeatherApiStatus.DONE
//                _properties.value = resultapi.daily
//
//
//
//            } catch (e: Exception) {
//                Log.e("API Error:", e.localizedMessage)
//                _status.value = WeatherApiStatus.ERROR
//            }
//        }
//    }


    fun displayPropertyDetails(forecast: Forecast) {
        _navigateToSelectedWeatherProperty.value = forecast
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedWeatherProperty.value = null
    }

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StartViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return StartViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}

