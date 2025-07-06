package com.ismail.favorite.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ismail.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {
    val getAllFavorite = movieUseCase.getAllFavorite().asLiveData()
}