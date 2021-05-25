package com.mirfanrafif.kicksfilm.ui.favorite

import androidx.lifecycle.ViewModel
import com.mirfanrafif.kicksfilm.data.repository.MovieRepository

class FavoriteViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getFavoriteMovies() = movieRepository.getFavoriteMovies()
    fun getFavoriteTvShows() = movieRepository.getFavoriteTvShows()
}