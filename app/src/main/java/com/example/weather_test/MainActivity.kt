package com.example.weather_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.weather_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        val navController = this.findNavController(R.id.nav_graph)
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.nav_graph)
//        return navController.navigateUp() }

}