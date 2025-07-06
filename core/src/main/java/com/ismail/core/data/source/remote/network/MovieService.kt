package com.ismail.core.data.source.remote.network

import com.ismail.core.data.source.remote.response.GetCastAndCrewResponse
import com.ismail.core.data.source.remote.response.GetMovieResponse
import com.ismail.core.data.source.remote.response.GetMovieVideoByIdResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("discover/movie?api_key=$api_key")
    suspend fun getMovieDiscover(): GetMovieResponse

    @GET("movie/{id}?api_key=$api_key")
    suspend fun getMovieDetailById(
        @Path("id") id:Int
    ): GetMovieResponse

    @GET("movie/{id}/credits?api_key=$api_key")
    suspend fun getCastAndCrew(
        @Path("id") id:Int
    ): GetCastAndCrewResponse

    @GET("movie/{id}/videos?api_key=$api_key")
    suspend fun getMovieVideoById(
        @Path("id") id:Int
    ): GetMovieVideoByIdResponse

    companion object{
        private const val api_key = "31809f947aa8bbdfce517a3bcb339f4b"
    }
}