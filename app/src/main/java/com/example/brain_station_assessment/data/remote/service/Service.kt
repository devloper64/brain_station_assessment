package com.example.brain_station_assessment.data.remote.service

import com.example.brain_station_assessment.data.dto.response.RepositoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface Service {


    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") q: String,
        @Query("sort")sort:String,
        @Query("order") order:String,
    ): Response<RepositoriesResponse>

}

