package com.example.weather_test.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
//https://samples.openweathermap.org/data/2.5/

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */


/**
 * A public interface that exposes the [getProperties] method
 */
interface WeatherApiService {
//              forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a1
    @GET("onecall?lat=36&lon=43.9&exclude=minutely,hourly&units=metric&appid=8a5fcd3afb779f8f3e06507be8f26388")
    fun getProperties(): Deferred<WeatherProperty>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object WeatherApi {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService =retrofit.create(WeatherApiService::class.java)
}

