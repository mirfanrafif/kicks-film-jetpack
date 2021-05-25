package com.mirfanrafif.kicksfilm.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mirfanrafif.kicksfilm.data.repository.MovieRepository
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.vo.Resource

class MoviesViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getAllMovies()
}