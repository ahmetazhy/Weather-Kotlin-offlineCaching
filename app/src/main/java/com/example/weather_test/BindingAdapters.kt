package com.example.weather_test

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_test.network.Forecast
import com.example.weather_test.network.WeatherProperty
import com.example.weather_test.startapp.WeatherAdapter
import com.example.weather_test.startapp.WeatherApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Forecast>?) {
    val adapter = recyclerView.adapter as WeatherAdapter
    adapter.submitList(data)
}

@BindingAdapter("WeatherApiStatus")
fun bindStatus(statusImageView: ImageView, status: WeatherApiStatus?) {
    when (status) {
        WeatherApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        WeatherApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        WeatherApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

