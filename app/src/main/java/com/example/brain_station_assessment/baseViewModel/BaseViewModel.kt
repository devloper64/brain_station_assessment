package com.example.brain_station_assessment.baseViewModel

import androidx.lifecycle.ViewModel
import com.example.brain_station_assessment.errors.ErrorManager
import javax.inject.Inject



abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var errorManager: ErrorManager

}
