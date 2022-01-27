package com.example.weather_test.domain

import android.os.Parcelable
import com.example.weather_test.network.Forecast

import com.example.weather_test.network.Weather

data class NewForecast(

    val weather: List<Weather>,
    var dt_txt: String,

    val dt:Int
)