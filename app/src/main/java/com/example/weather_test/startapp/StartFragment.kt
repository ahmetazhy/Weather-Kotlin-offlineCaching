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


        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        binding.lifecycleOwner = this
        return binding.root
    }
}

