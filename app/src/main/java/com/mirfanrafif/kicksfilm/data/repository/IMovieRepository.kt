package com.mirfanrafif.kicksfilm.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.vo.Resource

interface IMovieRepository {
    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>>

    fun getDetailTvShow(id: Int): LiveData<Resource<TvShowEntity>>

    fun updateMovie(movieEntity: MovieEntity)

    fun updateTvShow(tvShowEntity: TvShowEntity)

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>
}