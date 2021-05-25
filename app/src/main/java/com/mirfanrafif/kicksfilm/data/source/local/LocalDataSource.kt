package com.mirfanrafif.kicksfilm.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.data.source.local.dao.MovieDao
import com.mirfanrafif.kicksfilm.data.source.local.dao.TvShowDao

class LocalDataSource private constructor(
    private val movieDao: MovieDao, private val tvShowDao: TvShowDao){

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao, tvShowDao: TvShowDao): LocalDataSource {
            return instance ?: LocalDataSource(movieDao, tvShowDao)
        }
    }

    fun getAllMovies() : DataSource.Factory<Int, MovieEntity> = movieDao.getAllMovies()

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = movieDao.getFavoriteMovie()

    fun getMoviesById(id: Int): LiveData<MovieEntity> = movieDao.getMovieById(id)

    fun insertMovie(movieEntity: List<MovieEntity>) = movieDao.insert(movieEntity)

    fun updateMovie(movieEntity: MovieEntity) = movieDao.update(movieEntity)

    fun deleteMovie(movieEntity: MovieEntity) = movieDao.delete(movieEntity)

    fun getAllTvShow() : DataSource.Factory<Int, TvShowEntity> = tvShowDao.getAllTvShow()

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowEntity> = tvShowDao.getFavoriteMovie()

    fun getTvShowById(id: Int): LiveData<TvShowEntity> = tvShowDao.getTvShowById(id)

    fun insertTvShow(entity: List<TvShowEntity>) = tvShowDao.insert(entity)

    fun updateTvShow(entity: TvShowEntity) = tvShowDao.update(entity)

    fun deleteTvShow(entity: TvShowEntity) = tvShowDao.delete(entity)

}