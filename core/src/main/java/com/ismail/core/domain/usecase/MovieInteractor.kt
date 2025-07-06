package com.ismail.core.domain.usecase


import com.ismail.core.data.source.Resource
import com.ismail.core.domain.model.Caster
import com.ismail.core.domain.model.Movie
import com.ismail.core.domain.model.MovieVideo
import com.ismail.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val iMovieRepository: IMovieRepository):
    MovieUseCase {
    override fun getMovieDiscover(): Flow<Resource<List<Movie>>> =
        iMovieRepository.getMovieDiscover()

    override fun getCastAndCrew(id: Int): Flow<Resource<List<Caster>>> =
        iMovieRepository.getCastAndCrew(id)

    override fun getMovieVideoById(id: Int): Flow<Resource<List<MovieVideo>>> =
        iMovieRepository.getMovieVideoById(id)

    override suspend fun insertMovie(movie: Movie) =
        iMovieRepository.insertMovie(movie)

    override fun getAllFavorite(): Flow<List<Movie>> =
        iMovieRepository.getAllFavorite()

    override suspend fun delete(movie: Movie) =
        iMovieRepository.delete(movie)

    override fun isFavorite(id: Int): Flow<Boolean> =
        iMovieRepository.isFavorite(id)

}


//    override fun getMovieById(id: Int): Flow<Resource<List<Movie>>> =
//        iMovieRepository.getMovieById(id)