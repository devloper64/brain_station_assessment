package com.example.brain_station_assessment.data


import com.example.brain_station_assessment.data.dto.response.RepositoriesResponse
import kotlinx.coroutines.flow.Flow



interface DataRepositorySource {

    suspend fun getRepositories(q: String,sort:String,order:String): Flow<Resource<RepositoriesResponse>>
}
