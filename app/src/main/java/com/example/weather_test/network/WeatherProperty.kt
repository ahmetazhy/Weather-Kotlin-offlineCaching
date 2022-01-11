package com.example.weather_test.network

import android.os.Parcelable

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
    val dt_txt: String,
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


//fun WeatherProperty.asDomainModel(): List<WeatherPropertyData> {
//    return list.map {
//        WeatherPropertyData(
//            main = it.main,
//            weather = it.weather,
//            dt_txt = it.dt_txt,
//            wind = it.wind,
//            dt =it.dt
//        )
//    }
//}

//fun WeatherProperty.asDatabaseModel(): Array<DatabaseWeatherProperty> {
//    return list.map {
//        DatabaseWeatherProperty(
//            main = it.main,
//            weather = it.weather,
//            dt_txt = it.dt_txt,
//            wind = it.wind,
//            dt =it.dt
//        )
//    }.toTypedArray()
//}


/// for Weather

//
//fun Forecast.asDomainModel(): List<WeatherData> {
//    return weather.map {
//        WeatherData(
//            id = it.id,
//            main = it.main,
//            description = it.description,
//            icon = it.icon
//        )
//    }
//}
//
//fun Forecast.asDatabaseModel(): Array<DatabaseWeather> {
//    return weather.map {
//        DatabaseWeather(
//            id = it.id,
//            main = it.main,
//            description = it.description,
//            icon = it.icon
//        )
//    }.toTypedArray()
//}





/// for main


//fun Forecast.asDomainModel2(): MainData {
//       val  temp = main.temp
//       val temp_min = main.temp_min
//       val  temp_max = main.temp_max
//        val pressure = main.pressure
//    val humidity = main.humidity
//      val   temp_kf = main.temp_kf
//
//    return MainData(temp,temp_min,temp_max,pressure,humidity,temp_kf)
//}
//
//fun Forecast.asDatabaseModel2(): DatabaseMain {
//    val  temp = main.temp
//    val temp_min = main.temp_min
//    val  temp_max = main.temp_max
//    val pressure = main.pressure
//    val humidity = main.humidity
//    val   temp_kf = main.temp_kf
//
//    return DatabaseMain(temp,temp_min,temp_max,pressure,humidity,temp_kf)
//}



