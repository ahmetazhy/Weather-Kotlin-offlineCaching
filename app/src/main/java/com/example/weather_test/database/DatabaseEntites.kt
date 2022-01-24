package com.example.weather_test.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather_test.network.Forecast
import com.example.weather_test.network.Main
import com.example.weather_test.network.Weather
import com.example.weather_test.network.Wind



@Entity(tableName = "database_forecast")
data class DatabaseForecast(
    @PrimaryKey val dt: Int,
    val dt_txt: String,
    @Embedded val weather: DatabaseWeather?,
    @Embedded val wind: DatabaseWind?,
    @Embedded val main: DatabaseMain?
)

data class DatabaseMain(
    @ColumnInfo(name = "temp_db") val temp: Double?,
    @ColumnInfo(name = "temp_main_db")  val temp_min: Double?,
    @ColumnInfo(name = "temp_max_db")  val temp_max: Double?,
    @ColumnInfo(name = "pressure_db") val pressure: Double?,
    @ColumnInfo(name = "humidity_db")  val humidity: Int?,
    @ColumnInfo(name = "tempkf_db") val temp_kf: Double?)


data class DatabaseWind(
    @ColumnInfo(name = "speed-db")  val speed: Double,
    @ColumnInfo(name = "deg_db")  val deg: Double
)


data class DatabaseWeather(
    @ColumnInfo(name = "id_db")  val id: Int,
    @ColumnInfo(name = "main_db") val main: String,
    @ColumnInfo(name = "description_db") val description: String,
    @ColumnInfo(name = "icon_db") val icon: String
)


fun List<DatabaseForecast>.asDomainModel(): List<Forecast> {
    return map {
        val NewWind= Wind(it.wind!!.speed,it.wind.deg)
        val NewMain = Main(
            it.main!!.temp!!,
            it.main!!.temp_min!!, it.main.temp_max!!, it.main.temp_kf!!, it.main.humidity!!, it.main.pressure!!
        )
        val NewWeather= Weather(it.weather!!.id,it.weather.main,it.weather.description,it.weather.icon)

        Forecast(
            main = NewMain,
            weather = listOf(NewWeather),
            dt_txt = it.dt_txt,
            wind = NewWind,
            dt = it.dt
        )
    }
}


