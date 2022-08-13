package com.example.brain_station_assessment.data.remote


import com.example.brain_station_assessment.data.Resource
import com.example.brain_station_assessment.data.dto.response.RepositoriesResponse
import com.example.brain_station_assessment.data.error.NETWORK_ERROR
import com.example.brain_station_assessment.data.error.NO_INTERNET_CONNECTION
import com.example.brain_station_assessment.data.remote.service.Service
import com.example.brain_station_assessment.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class RemoteData @Inject
constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) :
    RemoteDataSource {



    override suspend fun getRepositories(
        q: String,
        sort: String,
        order: String
    ): Resource<RepositoriesResponse> {

        val service = serviceGenerator.createService(Service::class.java)
        return when (val response = processCall { service.getRepositories(q, sort, order) }) {
            is RepositoriesResponse -> {
                Resource.Success(data = response)
            }
            else -> {
                val split = response.toString().split(",");
                Resource.DataError(errorCode = split[0].toInt(), errorMsg = split[1])
            }
        }
    }



    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }



}
