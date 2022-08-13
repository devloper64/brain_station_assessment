package com.example.brain_station_assessment.data.remote

import com.example.brain_station_assessment.data.Resource
import com.example.brain_station_assessment.data.dto.response.RepositoriesResponse


internal interface RemoteDataSource {

    suspend fun getRepositories(q: String,sort:String,order:String): Resource<RepositoriesResponse>

}
