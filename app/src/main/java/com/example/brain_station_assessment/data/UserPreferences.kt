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




    val sortKey: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[SORT_KEY]
        }

    suspend fun saveSortKey(sortKey: String) {
        dataStore.edit { preferences ->
            preferences[SORT_KEY] = sortKey
        }
    }



    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


    companion object {
        private val SORT_KEY = preferencesKey<String>("key_sort")


    }

}