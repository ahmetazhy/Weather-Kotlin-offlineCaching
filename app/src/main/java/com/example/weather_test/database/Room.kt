package com.example.weather_test.database

import android.content.Context
import androidx.lifecycle.LiveData

//
//@Dao
//interface WeatherPropertyDao {
//
//    @androidx.room.Query("select * from databaseweatherproperty")
//    fun getWeatherPropertyData(): LiveData<List<DatabaseWeatherProperty>>
//
////    @Query("select * from databaseweather")
////    fun getWeatherdata(): List<DatabaseWeather>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(vararg weatherdata: DatabaseWeatherProperty)
//}
//
//
//@Database(entities = [WeatherDatabaseProperty::class], version = 1)
//abstract class WeatherDatabaseProperty : RoomDatabase() {
//    abstract val weatherpropertyDao: WeatherPropertyDao
//}
//
//private lateinit var INSTANCE: WeatherDatabaseProperty
//
//fun getDatabase(context: Context): WeatherDatabaseProperty {
//    synchronized(WeatherDatabaseProperty::class.java) {
//        if (!::INSTANCE.isInitialized) {
//            INSTANCE = Room.databaseBuilder(context.applicationContext,
//                WeatherDatabaseProperty::class.java,
//                "weatherproperty").build()
//        }
//    }
//    return INSTANCE
//}
//
//
////@Dao
////interface MainDao {
////
////    @androidx.room.Query("select * from DatabaseMain")
////    fun getMainData(): List<DatabaseMain>
////
////    @Insert(onConflict = OnConflictStrategy.REPLACE)
////    fun insertAll(vararg main: DatabaseMain)
////}