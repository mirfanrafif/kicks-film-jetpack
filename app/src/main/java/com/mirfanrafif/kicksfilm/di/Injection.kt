package com.mirfanrafif.kicksfilm.di

import android.content.Context
import com.mirfanrafif.kicksfilm.data.repository.MovieRepository
import com.mirfanrafif.kicksfilm.data.source.local.LocalDataSource
import com.mirfanrafif.kicksfilm.data.source.local.database.KicksFilmDB
import com.mirfanrafif.kicksfilm.data.source.remote.RemoteDataSource
import com.mirfanrafif.kicksfilm.data.source.remote.api.TmdbService
import com.mirfanrafif.kicksfilm.utils.AppExecutor

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = KicksFilmDB.getDatabase(context)

        val localDataSource = LocalDataSource.getInstance(database.movieDao(), database.tvShowDao())

        val appExecutor = AppExecutor()
        val tmdbService = TmdbService()
        val remoteDataSource = RemoteDataSource.getInstance(tmdbService)
        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutor)
    }
}