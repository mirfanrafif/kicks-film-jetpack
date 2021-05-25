package com.mirfanrafif.kicksfilm.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvshowentities ORDER BY tvShowId ASC")
    fun getAllTvShow() : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :movieId LIMIT 1")
    fun getTvShowById(movieId: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: List<TvShowEntity>)

    @Update
    fun update(entity: TvShowEntity)

    @Delete
    fun delete(entity: TvShowEntity)
}