package com.example.brain_station_assessment.errors

import com.example.brain_station_assessment.data.error.mapper.ErrorMapper
import javax.inject.Inject
import com.example.brain_station_assessment.data.error.Error


class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
