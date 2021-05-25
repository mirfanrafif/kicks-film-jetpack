package com.mirfanrafif.kicksfilm.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mirfanrafif.kicksfilm.data.repository.MovieRepository
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.vo.Resource

class DetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> =
        movieRepository.getDetailMovie(id)

    fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>> = movieRepository.getDetailTvShow(id)

    fun updateMovie(entity: MovieEntity) = movieRepository.updateMovie(entity)

    fun updateTvShow(entity: TvShowEntity) = movieRepository.updateTvShow(entity)
}