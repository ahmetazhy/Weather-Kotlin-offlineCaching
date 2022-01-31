package com.example.weather_test


import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_test.network.Forecast
import com.example.weather_test.startapp.WeatherAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Forecast>?) {
    val adapter = recyclerView.adapter as WeatherAdapter
    adapter.submitList(data)
}