package com.example.weather_test.startapp

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_test.databinding.ViewItemBinding
import com.example.weather_test.network.Forecast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class WeatherAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Forecast, WeatherAdapter.WeatherPropertyViewHolder>(DiffCallback) {
    class WeatherPropertyViewHolder(private var binding: ViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(forecast: Forecast) {
            // binding.wDescription.text = forecast.weather[position].description
            binding.wTempMax.text = forecast.main.temp_max.toInt().toString()
            binding.wTempMain.text = forecast.main.temp_min.toInt().toString()
            binding.wDescription.text = forecast.weather[0].description


            val gettoformat = forecast.dt_txt
            fun getAbbreviatedFromDateTime(dateTime: String, dateFormat: String, field: String): String? {
                val input = SimpleDateFormat(dateFormat)
                val output = SimpleDateFormat(field)
                try {
                    val getAbbreviate = input.parse(dateTime)    // parse input
                    return output.format(getAbbreviate)    // format output
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                return null
            }

            val dayOfWeek=getAbbreviatedFromDateTime(gettoformat,"yyyy-MM-dd HH:mm:ss","EEEE,"+" MMMM"+" d")
            val onedayOfWeek=getAbbreviatedFromDateTime(gettoformat,"yyyy-MM-dd HH:mm:ss","EEEE")


            binding.wDayType.text = onedayOfWeek.toString()
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
