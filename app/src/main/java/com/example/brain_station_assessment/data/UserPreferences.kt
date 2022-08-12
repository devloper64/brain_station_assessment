package com.example.brain_station_assessment.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.clear
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferences @Inject constructor(context: Context) {

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = applicationContext.createDataStore(
        name = "my_data_store"
    )











    val accessToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN]
        }

    val refreshToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN]
        }

    val userId: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[USER_ID]
        }

    val userPoint: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[POINT]
        }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }




    suspend fun saveUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }

    suspend fun saveUserPoint(point: String) {
        dataStore.edit { preferences ->
            preferences[POINT] = point
        }
    }


    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


    companion object {
        private val ACCESS_TOKEN = preferencesKey<String>("key_access_token")
        private val REFRESH_TOKEN = preferencesKey<String>("key_refresh_token")
        private val USER_ID = preferencesKey<String>("key_user_id")
        private val POINT = preferencesKey<String>("key_user_point")

    }

}