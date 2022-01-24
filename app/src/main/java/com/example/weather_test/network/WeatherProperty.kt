package com.example.weather_test.network

import android.os.Parcelable
import com.example.weather_test.database.DatabaseForecast
import com.example.weather_test.database.DatabaseMain
import com.example.weather_test.database.DatabaseWeather
import com.example.weather_test.database.DatabaseWind

import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherProperty(
    val cod: String,
    val list: List<Forecast>
):  Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Forecast(
    val main: Main,
    val weather: List<Weather>,
    var dt_txt: String,
    val wind: Wind,
    val dt:Int
) :  Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Main(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Int,
    val temp_kf: Double
):  Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
):  Parcelable

@Parcelize
data class Wind(
    val speed: Double,
    val deg: Double
):  Parcelable



fun WeatherProperty.asDatabaseModel(): Array<DatabaseForecast> {
    return list.map {
        val newMain = DatabaseMain(it.main.temp, it.main.temp_min,it.main.temp_max,it.main.temp_kf,it.main.humidity,it.main.pressure )
        val newWind= DatabaseWind(it.wind.speed,it.wind.deg)
        val newWeather=
            DatabaseWeather(it.weather[0].id,it.weather[0].main,it.weather[0].description,it.weather[0].icon)
        DatabaseForecast(
            main = newMain,
            weather = newWeather,
            dt_txt = it.dt_txt,
            dt =it.dt,
            wind = newWind
        )
    }.toTypedArray()
}



