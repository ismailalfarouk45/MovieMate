package com.ismail.core.data.source.remote

import com.ismail.core.data.source.remote.response.GetCastAndCrewResponse
import com.ismail.core.data.source.remote.response.GetMovieResponse
import com.ismail.core.data.source.remote.response.GetMovieVideoByIdResponse
import com.ismail.core.data.source.remote.network.ApiResponse
import com.ismail.core.data.source.remote.network.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val movieService: MovieService) {

    suspend fun getMovieDiscover(): Flow<ApiResponse<GetMovieResponse>> {
        return flow {
            try {
                val response = movieService.getMovieDiscover()
                emit(ApiResponse.Success(response))
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCastAndCrew(id:Int): Flow<ApiResponse<GetCastAndCrewResponse>>{
        return channelFlow {
            try {
                val response = movieService.getCastAndCrew(id)
                trySend(ApiResponse.Success(response))
            }catch (e:Exception){
                trySend(ApiResponse.Error(e.toString()))
            }
            awaitClose()
        }
    }

    suspend fun getMovieVideoById(id:Int): Flow<ApiResponse<GetMovieVideoByIdResponse>>{
        return channelFlow {
            try {
                val response = movieService.getMovieVideoById(id)
                trySend(ApiResponse.Success(response))
            }catch (e:Exception){
                trySend(ApiResponse.Error(e.toString()))
            }
            awaitClose()
        }
    }
}