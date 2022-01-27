package com.example.weather_test.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather_test.R
import com.example.weather_test.databinding.DetailFragmentBinding
import com.example.weather_test.startapp.StartViewModel

class Detailfragment : Fragment() {
    private lateinit var viewModell: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner=this

        val forecast = DetailfragmentArgs.fromBundle(arguments!!).selectedProperty

        val viewModelFactory = DetailViewModelFactory(forecast, application)

        binding.viewModell = ViewModelProvider(
            this, viewModelFactory).get(DetailViewModel::class.java)


//        viewModell.selectedProperty.observe(viewLifecycleOwner, Observer {
//            var dateday = it.weather[0].description
//            val lightrain = "light rain"
//            if (lightrain == dateday) {
//                binding.imageViewdetail.setImageResource(R.drawable.art_light_rain)
//            }
//
//
//        })





        return binding.root
    }
}

