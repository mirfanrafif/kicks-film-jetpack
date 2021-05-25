package com.mirfanrafif.kicksfilm.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mirfanrafif.kicksfilm.data.repository.MovieRepository
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> = movieRepository.getAllTvShows()
}