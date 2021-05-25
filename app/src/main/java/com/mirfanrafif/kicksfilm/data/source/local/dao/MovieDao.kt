package com.mirfanrafif.kicksfilm.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieEntities ORDER BY movieId ASC")
    fun getAllMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieEntities WHERE movieId = :movieId LIMIT 1")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM movieEntities WHERE isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: List<MovieEntity>)

    @Update
    fun update(movieEntity: MovieEntity)

    @Delete
    fun delete(movieEntity: MovieEntity)
}