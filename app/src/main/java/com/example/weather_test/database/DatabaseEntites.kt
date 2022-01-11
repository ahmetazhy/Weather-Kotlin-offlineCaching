package com.example.weather_test.database

//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.example.weather_test.domain.WeatherPropertyData
//import com.example.weather_test.network.Main
//import com.example.weather_test.network.Weather
//import com.example.weather_test.network.Wind
//
//@Entity
//data class DatabaseWeatherProperty(
//    @PrimaryKey
//    val main: Main,
//    val weather: List<Weather>,
//    val dt_txt: String,
//    val wind: Wind,
//    val dt:Int
//    )
//
//fun List<DatabaseWeatherProperty>.asDomainModel(): List<WeatherPropertyData> {
//    return map {
//        WeatherPropertyData(
//            main = it.main,
//            weather = it.weather,
//            dt_txt = it.dt_txt,
//            wind = it.wind,
//            dt =it.dt
//        )
//    }
//}


//@Entity
//data class DatabaseWeather constructor(
//    @PrimaryKey
//    val id: Int,
//    val main: String,
//    val description: String,
//    val icon: String)
//
//fun List<DatabaseWeather>.asDomainModel(): List<WeatherData> {
//    return map {
//        WeatherData(
//            id = it.id,
//            main = it.main,
//            description = it.description,
//            icon = it.icon)
//    }
//}

//
//@Entity
//data class DatabaseMain constructor(
//    @PrimaryKey
//    val temp: Double,
//    val temp_min: Double,
//    val temp_max: Double,
//    val pressure: Double,
//    val humidity: Int,
//    val temp_kf: Double
//    )
//
//fun List<DatabaseWeather>.asDomainModel2(): MainData {
//    val  temp = main.temp
//    val temp_min = main.temp_min
//    val  temp_max = main.temp_max
//    val pressure = main.pressure
//    val humidity = main.humidity
//    val   temp_kf = main.temp_kf
//
//    return MainData(temp,temp_min,temp_max,pressure,humidity,temp_kf)
//}
//


