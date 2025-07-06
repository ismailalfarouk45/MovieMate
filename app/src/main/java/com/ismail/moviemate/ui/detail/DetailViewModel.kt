package com.ismail.moviemate.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ismail.core.data.source.Resource
import com.ismail.core.domain.model.Caster
import com.ismail.core.domain.model.Movie
import com.ismail.core.domain.model.MovieVideo
import com.ismail.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {
    private val _movieTrailerResponse = MutableStateFlow<Resource<List<MovieVideo>>>(Resource.Loading())
    val movieTrailerResponse: StateFlow<Resource<List<MovieVideo>>> = _movieTrailerResponse.asStateFlow()

    private val _castResponse = MutableStateFlow<Resource<List<Caster>>>(Resource.Loading())
    val castResponse: StateFlow<Resource<List<Caster>>> = _castResponse.asStateFlow()

    fun getMovieTrailerById(id:Int) = viewModelScope.launch(Dispatchers.IO) {
        movieUseCase.getMovieVideoById(id).collect { result ->
            _movieTrailerResponse.value = result
        }
    }


    fun getCastAndCrew(id:Int) = viewModelScope.launch(Dispatchers.IO) {
        movieUseCase.getCastAndCrew(id).collect{ result ->
            _castResponse.value = result
        }
    }

    fun insertMovie(movie:Movie) = viewModelScope.launch {
        movieUseCase.insertMovie(movie)
    }

    fun deleteFavorite(movie: Movie) = viewModelScope.launch {
        movieUseCase.delete(movie)
    }

    fun isFavorite(id:Int) = movieUseCase.isFavorite(id).asLiveData()
}