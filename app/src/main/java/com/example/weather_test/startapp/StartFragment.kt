package com.example.weather_test.startapp

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.weather_test.R
import com.example.weather_test.databinding.StartFragmentBinding
import kotlinx.android.synthetic.main.detail_fragment.view.*
import kotlinx.android.synthetic.main.start_fragment.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class StartFragment : Fragment() {
    private lateinit var binding: StartFragmentBinding
    private lateinit var viewModel: StartViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StartFragmentBinding.inflate(
            inflater, container, false
        )

        viewModel = ViewModelProvider(this).get(StartViewModel::class.java)

        binding.weekList.adapter = WeatherAdapter(WeatherAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })


        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController()
                    .navigate(StartFragmentDirections.actionStartFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        binding.viewModel = viewModel





        viewModel.properties.observe(viewLifecycleOwner, Observer {
            var dateday = it[0].weather[0].description
            val lightrain = "light rain"
            val overcastclouds = "overcast clouds"
            val brokencloud = "broken cloud"
            val clearsky = "clear sky"
            val scatteredclouds = "scattered clouds"
            val rain = "rain"

            if (lightrain == dateday) {
                binding.imageViewtoday.setImageResource(R.drawable.art_light_rain)
            }
            else if (overcastclouds == dateday) {
                binding.imageViewtoday.setImageResource(R.drawable.art_light_clouds)
            }
            else if (brokencloud == dateday) {
                binding.imageViewtoday.setImageResource(R.drawable.art_clouds)
            }
            else if (clearsky == dateday) {
                binding.imageViewtoday.setImageResource(R.drawable.art_clear)
            }
            else if (scatteredclouds == dateday) {
                binding.imageViewtoday.setImageResource(R.drawable.art_clouds)
            }
            else if (rain == dateday) {
                binding.imageViewtoday.setImageResource(R.drawable.art_rain)
            }

        })
//
//        binding.setDisplayIt()


        viewModel.properties.observe(viewLifecycleOwner, Observer {
            var newTempmax = it[0].temp.max

            binding.setTempmax(((newTempmax.toInt().toString()) + "\u00B0"))

        })

        viewModel.properties.observe(viewLifecycleOwner, Observer {
            var newTempmin = it[0].temp.min

            binding.setTempmin(((newTempmin.toInt().toString()) + "\u00B0"))

        })


        binding.weekList.adapter = WeatherAdapter(WeatherAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })





        binding.weekList.adapter = WeatherAdapter(WeatherAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })




        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}

