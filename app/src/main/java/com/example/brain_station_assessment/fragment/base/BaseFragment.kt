package com.example.brain_station_assessment.fragment.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment



abstract class BaseFragment: Fragment() {
    abstract fun observeViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }
}