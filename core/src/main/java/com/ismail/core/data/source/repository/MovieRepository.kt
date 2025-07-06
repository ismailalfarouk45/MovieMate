package com.ismail.core.data.source.repository

import com.ismail.core.data.source.remote.response.GetCastAndCrewResponse
import com.ismail.core.data.source.remote.response.GetMovieResponse
import com.ismail.core.data.source.remote.response.GetMovieVideoByIdResponse
import com.ismail.core.data.source.NetworkBoundResource
import com.ismail.core.data.source.Resource
import com.ismail.core.data.source.local.LocalDataSource
import com.ismail.core.data.source.remote.RemoteDataSource
import com.ismail.core.data.source.remote.network.ApiResponse
import com.ismail.core.domain.utils.DataMapper
import com.ismail.core.domain.model.Caster
import com.ismail.core.domain.model.Movie
import com.ismail.core.domain.model.MovieVideo
import com.ismail.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IMovieRepository{
    override fun getMovieDiscover(): Flow<Resource<List<Movie>>> =
        object: NetworkBoundResource<List<Movie>, GetMovieResponse>(){
            override suspend fun createCall(): Flow<ApiResponse<GetMovieResponse>> =
                remoteDataSource.getMovieDiscover()

            override fun loadFromNetwork(data: GetMovieResponse): Flow<List<Movie>> =
                DataMapper.mapListResponseToDomain(data.results)
        }.asFlow()

    override fun getCastAndCrew(id: Int): Flow<Resource<List<Caster>>> =
        object: NetworkBoundResource<List<Caster>, GetCastAndCrewResponse>(){
            override suspend fun createCall(): Flow<ApiResponse<GetCastAndCrewResponse>> =
                remoteDataSource.getCastAndCrew(id)

            override fun loadFromNetwork(data: GetCastAndCrewResponse): Flow<List<Caster>> =
                DataMapper.mapListCastAndCrewResponseToDomain(data.cast)
        }.asFlow()

    override fun getMovieVideoById(id: Int): Flow<Resource<List<MovieVideo>>> =
        object: NetworkBoundResource<List<MovieVideo>, GetMovieVideoByIdResponse>(){
            override suspend fun createCall(): Flow<ApiResponse<GetMovieVideoByIdResponse>> =
                remoteDataSource.getMovieVideoById(id)

            override fun loadFromNetwork(data: GetMovieVideoByIdResponse): Flow<List<MovieVideo>> =
                DataMapper.mapListMovieVideoToDomain(data.results)
        }.asFlow()

    override suspend fun insertMovie(movie: Movie) =
        localDataSource.insertMovie(DataMapper.mapDomainToEntity(movie))

    override fun getAllFavorite(): Flow<List<Movie>> =
        localDataSource.getAllFavorite().map {
            DataMapper.mapListEntityToDomain(it)
        }

    override suspend fun delete(movie: Movie) =
        localDataSource.delete(DataMapper.mapDomainToEntity(movie))

    override fun isFavorite(id: Int): Flow<Boolean> =
        localDataSource.isFavorite(id)
}