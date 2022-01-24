package com.example.weather_test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weather_test.database.ForecastDatabase
import com.example.weather_test.database.asDomainModel

import com.example.weather_test.network.Forecast

import com.example.weather_test.network.WeatherApi
import com.example.weather_test.network.asDatabaseModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherPropertyRepository (private val forecastDatabase: ForecastDatabase) {


    val weatherPropertyData: LiveData<List<Forecast>> =
        Transformations.map(forecastDatabase.forecastDao.getForecast()) {
            it.asDomainModel()
        }

    suspend fun refreshWeatherProperty() {
        withContext(Dispatchers.IO) {
            val forecastlist = WeatherApi.retrofitService.getProperties().await()
            forecastDatabase.forecastDao.insertAll(*forecastlist.asDatabaseModel())
        }
    }
}