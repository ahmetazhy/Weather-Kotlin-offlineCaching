package com.example.weather_test.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ForecastDao {

    @androidx.room.Query("select * from database_forecast")
    fun getForecast(): LiveData<List<DatabaseForecast>>

//    @Query("select * from databaseweather")
//    fun getWeatherdata(): List<DatabaseWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg forecast: DatabaseForecast)
}


@Database(entities = [DatabaseForecast::class], version = 1)
abstract class ForecastDatabase : RoomDatabase() {
    abstract val forecastDao: ForecastDao
}

private lateinit var INSTANCE: ForecastDatabase

fun getDatabase(context: Context): ForecastDatabase {
    synchronized(ForecastDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java,
                "forecast").build()
        }
    }
    return INSTANCE
}
