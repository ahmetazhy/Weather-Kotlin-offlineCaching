package com.example.weather_test.network

import android.os.Parcelable
import com.example.weather_test.database.DatabaseForecast
import com.example.weather_test.database.DatabaseTemp
import com.example.weather_test.database.DatabaseWeather
import com.squareup.moshi.JsonClass


import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class WeatherProperty(
    val daily: List<Forecast>
):  Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Forecast(
    val weather: List<Weather>,
    val dt:Int,
    val temp:Temp,
    val pressure: Double,
    val humidity: Int,
    val wind_speed: Double,
    val wind_deg: Double,
   val dew_point: Double,
val wind_gust: Double

    ) :  Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon:String
):  Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Temp(
    val day: Double,
    val min: Double,
    val max: Double
):  Parcelable




fun WeatherProperty.asDatabaseModel(): Array<DatabaseForecast> {
    return daily.map {
        val newTemp = DatabaseTemp(it.temp.day,it.temp.min,it.temp.max)
        val newWeather=
            DatabaseWeather(it.weather[0].id,it.weather[0].main,it.weather[0].description,it.weather[0].icon)
        DatabaseForecast(
            weather = newWeather,
            dt =it.dt,
            temp=newTemp,
            dew_point = it.dew_point,
            humidity = it.humidity,
            pressure = it.pressure,
            wind_deg = it.wind_deg,
            wind_gust = it.wind_gust,
            wind_speed = it.wind_speed
        )
    }.toTypedArray()
}



