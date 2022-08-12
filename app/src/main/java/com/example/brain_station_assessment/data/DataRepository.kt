package com.example.brain_station_assessment.data



import com.example.brain_station_assessment.data.dto.reponse.RepositoriesResponse
import com.example.brain_station_assessment.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext




class DataRepository @Inject constructor(private val remoteRepository: RemoteData, private val ioDispatcher: CoroutineContext) :
    DataRepositorySource {


    override suspend fun getRepositories(
        q: String,
        sort: String,
        order: String
    ): Flow<Resource<RepositoriesResponse>> {
        return flow {
            emit(remoteRepository.getRepositories(q, sort, order))
        }.flowOn(ioDispatcher)
    }


}
