package com.ismail.core.domain.usecase

import com.ismail.core.data.source.Resource
import com.ismail.core.domain.model.Caster
import com.ismail.core.domain.model.Movie
import com.ismail.core.domain.model.MovieVideo
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovieDiscover(): Flow<Resource<List<Movie>>>
    fun getCastAndCrew(id:Int): Flow<Resource<List<Caster>>>
    fun getMovieVideoById(id:Int): Flow<Resource<List<MovieVideo>>>
    suspend fun insertMovie(movie: Movie)
    fun getAllFavorite(): Flow<List<Movie>>
    suspend fun delete(movie: Movie)
    fun isFavorite(id:Int): Flow<Boolean>
}