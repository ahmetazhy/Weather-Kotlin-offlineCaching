package com.example.weather_test.detail

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.weather_test.R
import com.example.weather_test.databinding.DetailFragmentBinding
import java.text.SimpleDateFormat

import java.util.*

class Detailfragment : Fragment() {
    private lateinit var viewModell: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val application = requireNotNull(activity).application
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val forecast = DetailfragmentArgs.fromBundle(requireArguments()).selectedProperty

        val viewModelFactory = DetailViewModelFactory(forecast, application)

        viewModell = ViewModelProvider(
            this, viewModelFactory
        )[DetailViewModel::class.java]

        binding.viewModell = this.viewModell



        viewModell.selectedProperty.observe(viewLifecycleOwner) {
            val newTempmax = it.temp.max

            binding.tempmax = ((newTempmax.toInt().toString()) + "\u00B0")

        }

        viewModell.selectedProperty.observe(viewLifecycleOwner) {
            val newTempmin = it.temp.min

            binding.tempmin = ((newTempmin.toInt().toString()) + "\u00B0")

        }


        viewModell.selectedProperty.observe(viewLifecycleOwner) {
            val newHumidity = it.humidity
            binding.setHumidity(("$newHumidity %"))
        }
        viewModell.selectedProperty.observe(viewLifecycleOwner) {
            val newPressure = it.pressure
            binding.setPressure(((newPressure.toInt().toString()) + " hPa"))
        }
        viewModell.selectedProperty.observe(viewLifecycleOwner) {
            val newWind = it.wind_speed
            binding.setWind(((newWind.toInt().toString()) + " km/h E"))
        }


        viewModell.selectedProperty.observe(viewLifecycleOwner) {
            val dtdatetype: Long = it.dt.toLong()
            val date = Date(dtdatetype * 1000L)
            val jdf = SimpleDateFormat("EEEE," + " MMMM" + " d")
            jdf.timeZone = TimeZone.getTimeZone("GMT-4")
            val detaildatedate = jdf.format(date)
            binding.detaildate = detaildatedate.toString()
        }



        viewModell.selectedProperty.observe(viewLifecycleOwner) {
            val dateday = it.weather[0].description
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
                    binding.imageViewdetail.setImageResource(R.drawable.art_light_rain)
                }
                overcastclouds == dateday -> {
                    binding.imageViewdetail.setImageResource(R.drawable.art_light_clouds)
                }
                brokencloud == dateday -> {
                    binding.imageViewdetail.setImageResource(R.drawable.art_clouds)
                }
                clearsky == dateday -> {
                    binding.imageViewdetail.setImageResource(R.drawable.art_clear)
                }
                scatteredclouds == dateday -> {
                    binding.imageViewdetail.setImageResource(R.drawable.art_clouds)
                }
                rain == dateday -> {
                    binding.imageViewdetail.setImageResource(R.drawable.art_rain)
                }
                fewclouds == dateday -> {
                    binding.imageViewdetail.setImageResource(R.drawable.art_light_clouds)
                }
                moderaterain==dateday -> {
                    binding.imageViewdetail.setImageResource(R.drawable.art_rain)
                }
            }
        }


        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        inflater.inflate(R.menu.share, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}

