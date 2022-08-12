package com.example.brain_station_assessment.data.error.mapper

import android.content.Context
import com.example.brain_station_assessment.R
import com.example.brain_station_assessment.data.error.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMapper @Inject constructor(@ApplicationContext val context: Context) :
    ErrorMapperSource {

    override fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
            Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
            Pair(NETWORK_ERROR, getErrorString(R.string.network_error)),
            Pair(PASS_WORD_ERROR, getErrorString(R.string.invalid_password)),
            Pair(USER_NAME_ERROR, getErrorString(R.string.invalid_username)),
            Pair(CHECK_YOUR_FIELDS, getErrorString(R.string.invalid_username_and_password)),
            Pair(EMAIL_VALID_ERROR, getErrorString(R.string.invalid_email)),
            Pair(FIRST_NAME_VALID_ERROR, getErrorString(R.string.invalid_FirstName)),
            Pair(LAST_NAME_VALID_ERROR, getErrorString(R.string.invalid_lastName)),
        ).withDefault { getErrorString(R.string.network_error) }
}
