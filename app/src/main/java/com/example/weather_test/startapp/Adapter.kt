package com.example.weather_test.startapp

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_test.R
import com.example.weather_test.databinding.ViewItemBinding
import com.example.weather_test.network.Forecast
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class WeatherAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Forecast, WeatherAdapter.WeatherPropertyViewHolder>(DiffCallback) {

    class WeatherPropertyViewHolder(private var binding: ViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(forecast: Forecast) {

            binding.wTempMax.text = ((forecast.temp.max.toInt().toString())+ "\u00B0")

            binding.wTempMain.text = ((forecast.temp.min.toInt().toString())+ "\u00B0")
            binding.wDescription.text = forecast.weather[0].description
//
            val dtdate: Long = forecast.dt.toLong()
            val date = Date(dtdate * 1000L)
            val jdf = SimpleDateFormat("EEEE")
            jdf.timeZone = TimeZone.getTimeZone("GMT-4")
            val weekdate = jdf.format(date)
            binding.wDayType.text = weekdate

            val icon=forecast.weather[0].description
            val lightrain = "light rain"
            val overcastclouds = "overcast clouds"
            val brokencloud = "broken clouds"
            val clearsky = "clear sky"
            val scatteredclouds = "scattered clouds"
            val rain = "rain"
            val moderaterain = "moderate rain"
            val fewclouds = "few clouds"


            when {
                lightrain==icon -> {
                    binding.wIcon.setImageResource(R.drawable.art_light_rain)
                }
                overcastclouds==icon -> {
                    binding.wIcon.setImageResource(R.drawable.art_light_clouds)
                }
                fewclouds==icon -> {
                    binding.wIcon.setImageResource(R.drawable.art_light_clouds)
                }
                brokencloud==icon -> {
                    binding.wIcon.setImageResource(R.drawable.art_clouds)
                }
                clearsky==icon -> {
                    binding.wIcon.setImageResource(R.drawable.art_clear)
                }
                scatteredclouds==icon -> {
                    binding.wIcon.setImageResource(R.drawable.art_clouds)
                }
                rain==icon -> {
                    binding.wIcon.setImageResource(R.drawable.art_rain)
                }
                moderaterain==icon -> {
                    binding.wIcon.setImageResource(R.drawable.art_rain)
                }
            }


            binding.executePendingBindings()
        }
    }




    companion object DiffCallback : DiffUtil.ItemCallback<Forecast>() {
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Forecast,newItem: Forecast): Boolean {
            return oldItem.dt == newItem.dt
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherPropertyViewHolder {
        return WeatherPropertyViewHolder(ViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: WeatherPropertyViewHolder, position: Int) {
        val forecast = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(forecast)
        }
        holder.bind(forecast)
    }

    class OnClickListener(val clickListener: (forecast: Forecast) -> Unit) {
        fun onClick(forecast: Forecast) = clickListener(forecast)
    }

}
