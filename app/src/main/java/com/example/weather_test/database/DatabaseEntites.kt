package com.example.weather_test.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather_test.network.Forecast
import com.example.weather_test.network.Temp
import com.example.weather_test.network.Weather



@Entity(tableName = "database_forecast")
data class DatabaseForecast(
    @PrimaryKey val dt: Int,
    val pressure: Double,
    val humidity: Int,
    val wind_speed: Double,
    val wind_deg: Double,
    val dew_point: Double,
    val wind_gust: Double,
    @Embedded val weather: DatabaseWeather?,
    @Embedded val temp: DatabaseTemp?
)

data class DatabaseTemp(
    @ColumnInfo(name = "day_db") val day: Double,
    @ColumnInfo(name = "min_db") val min: Double,
    @ColumnInfo(name = "max_db") val max: Double
    )




data class DatabaseWeather(
    @ColumnInfo(name = "id_db") val id: Int,
    @ColumnInfo(name = "main_db") val main: String,
    @ColumnInfo(name = "description_db") val description: String,
    @ColumnInfo(name = "icon_db") val icon: String
)


fun List<DatabaseForecast>.asDomainModel(): List<Forecast> {
    return map {
            val NewMTemp = Temp(it.temp!!.day,it.temp.min,it.temp.max)
        val NewWeather= Weather(it.weather!!.id,it.weather.main,it.weather.description,it.weather.icon)

        Forecast(
            temp = NewMTemp,
            weather = listOf(NewWeather),
            dt = it.dt,
            wind_speed = it.wind_speed,
            wind_gust = it.wind_gust,
            wind_deg = it.wind_deg,
            pressure = it.pressure,
            humidity = it.humidity,
            dew_point = it.dew_point

        )
    }
}


