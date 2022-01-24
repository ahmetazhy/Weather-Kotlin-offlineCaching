package com.example.weather_test.work

import android.app.Application
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.weather_test.database.getDatabase
import com.example.weather_test.repository.WeatherPropertyRepository
import retrofit2.HttpException
import timber.log.Timber


class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to do your work.
     */
    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = WeatherPropertyRepository(database)
        return try {
            repository.refreshWeatherProperty()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}
