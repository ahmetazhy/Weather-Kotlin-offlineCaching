package com.example.weather_test.repository

import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

import com.example.weather_test.network.WeatherApi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//class WeatherPropertyRepository (private val databaseweatherProperty: WeatherDatabaseProperty) {
//
//
//    val weatherPropertyData: LiveData<List<WeatherPropertyData>> =
//        Transformations.map(databaseweatherProperty.weatherpropertyDao.getWeatherPropertyData()) {
//            it.asDomainModel()
//        }
//
//    suspend fun refreshWeatherProperty() {
//        withContext(Dispatchers.IO) {
//            val weatherpropertylist = WeatherApi.retrofitService.getProperties().await()
//            databaseweatherProperty.weatherpropertyDao.insertAll(*weatherpropertylist.asDatabaseModel())
//        }
//    }
//}