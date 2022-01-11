package com.example.weather_test.startapp

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.weather_test.R
import com.example.weather_test.databinding.StartFragmentBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class StartFragment : Fragment() {
    private lateinit var binding: StartFragmentBinding
    private lateinit var viewModel: StartViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StartFragmentBinding.inflate(
            inflater, container, false)

        viewModel = ViewModelProvider(this).get(StartViewModel::class.java)

        binding.weekList.adapter = WeatherAdapter(WeatherAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(StartFragmentDirections.actionStartFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        binding.viewModel = viewModel
        viewModel.properties.observe(viewLifecycleOwner, Observer {
            var dateday=it[0].dt_txt
            val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val datetoday = LocalDate.parse(dateday , firstApiFormat)
            binding.setDatetoday(datetoday.toString())
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
        inflater.inflate(R.menu.menu, menu) }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}

