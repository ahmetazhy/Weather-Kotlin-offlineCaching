package com.example.weather_test


import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_test.network.Forecast
import com.example.weather_test.startapp.WeatherAdapter
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Forecast>?) {
    val adapter = recyclerView.adapter as WeatherAdapter
    adapter.submitList(data)
}


@BindingAdapter("formatDate")
fun TextView.setDate(order_date: Int) {
    val dtdate: Long = order_date.toLong()
    val date = Date(dtdate * 1000L)
    val jdf = SimpleDateFormat("EEEE," + " MMMM" + " d")
    jdf.timeZone = TimeZone.getTimeZone("GMT-4")
    val weekdate = jdf.format(date)
    this.text = weekdate.toString()
}


@BindingAdapter("formatTemp")
fun TextView.setTemp(order_date: Double) {
    val temp = order_date
    this.text = ((temp.toInt().toString()) + "\u00B0")
}

@BindingAdapter("formatHumidity")
fun TextView.setHumidity(order_date: Int) {
    val humidity = order_date
    this.text = ((humidity.toString()) + " %")
}

@BindingAdapter("formatPressure")
fun TextView.setPressure(order_date: Double) {
    val pressure = order_date
    this.text = ((pressure.toInt().toString()) + " hPa")
}
@BindingAdapter("formatWind")
fun TextView.setWind(order_date: Double) {
    val wind = order_date
    this.text = ((wind.toInt().toString()) + " km/h E")
}



@BindingAdapter("formatIcon")
fun ImageView.setIcon(order_date: String? = "") {
    val desc = order_date
    val lightrain = "light rain"
    val overcastclouds = "overcast clouds"
    val brokencloud = "broken clouds"
    val clearsky = "clear sky"
    val scatteredclouds = "scattered clouds"
    val rain = "rain"
    val fewclouds = "few clouds"
    val moderaterain = "moderate rain"

    when {
        lightrain == desc -> {
            this.setImageResource(R.drawable.art_light_rain)
        }
        overcastclouds == desc -> {
            this.setImageResource(R.drawable.art_light_clouds)
        }
        brokencloud == desc -> {
            this.setImageResource(R.drawable.art_clouds)
        }
        clearsky == desc -> {
            this.setImageResource(R.drawable.art_clear)
        }
        scatteredclouds == desc -> {
            this.setImageResource(R.drawable.art_clouds)
        }
        rain == desc -> {
            this.setImageResource(R.drawable.art_rain)
        }
        fewclouds == desc -> {
            this.setImageResource(R.drawable.art_light_clouds)
        }
        moderaterain == desc -> {
            this.setImageResource(R.drawable.art_rain)
        }
        else -> {

        }

    }
}
