package com.example.weather_test.startapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weather_test.R
import com.example.weather_test.databinding.StartFragmentBinding
import java.text.SimpleDateFormat
import java.util.*


class StartFragment : Fragment() {
    private lateinit var binding: StartFragmentBinding
    private lateinit var viewModel: StartViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = StartFragmentBinding.inflate(
            inflater, container, false
        )

        viewModel = ViewModelProvider(this).get(StartViewModel::class.java)


        binding.viewModel = viewModel
        binding.weekList.adapter = WeatherAdapter(WeatherAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })


        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController()
                    .navigate(StartFragmentDirections.actionStartFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        }


        viewModel.properties.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {

                val dateday = it[0].weather[0].description
                val lightrain = "light rain"
                val overcastclouds = "overcast clouds"
                val brokencloud = "broken clouds"
                val clearsky = "clear sky"
                val scatteredclouds = "scattered clouds"
                val rain = "rain"
                val fewclouds = "few clouds"
                val moderaterain = "moderate rain"

                when {
                    lightrain == dateday -> {
                        binding.imageViewtoday.setImageResource(R.drawable.art_light_rain)
                    }
                    overcastclouds == dateday -> {
                        binding.imageViewtoday.setImageResource(R.drawable.art_light_clouds)
                    }
                    brokencloud == dateday -> {
                        binding.imageViewtoday.setImageResource(R.drawable.art_clouds)
                    }
                    clearsky == dateday -> {
                        binding.imageViewtoday.setImageResource(R.drawable.art_clear)
                    }
                    scatteredclouds == dateday -> {
                        binding.imageViewtoday.setImageResource(R.drawable.art_clouds)
                    }
                    rain == dateday -> {
                        binding.imageViewtoday.setImageResource(R.drawable.art_rain)
                    }
                    fewclouds == dateday -> {
                        binding.imageViewtoday.setImageResource(R.drawable.art_light_clouds)
                    }
                    moderaterain==dateday -> {
                        binding.imageViewtoday.setImageResource(R.drawable.art_rain)
                    }
                }
            }
        }


        viewModel.properties.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                val newTempmax = it[0].temp.max

                binding.tempmaxa = ((newTempmax.toInt().toString()) + "\u00B0")
            }
        }

        viewModel.properties.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                val newTempmin = it[0].temp.min

                binding.tempmina = ((newTempmin.toInt().toString()) + "\u00B0")
            }

        }


        viewModel.properties.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
            val dtdate: Long = it[0].dt.toLong()

            val date = Date(dtdate * 1000L)
            val jdf = SimpleDateFormat("EEEE," + " MMMM" + " d")
            jdf.timeZone = TimeZone.getTimeZone("GMT-4")
            val todaydate = jdf.format(date)
            binding.daydatea = todaydate
                }
        }



        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        binding.lifecycleOwner = this
        return binding.root
    }
}

