package com.example.brain_station_assessment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brain_station_assessment.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}