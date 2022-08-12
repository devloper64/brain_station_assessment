package com.example.brain_station_assessment.errors
import com.example.brain_station_assessment.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
