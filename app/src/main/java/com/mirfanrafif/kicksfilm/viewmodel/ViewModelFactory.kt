package com.mirfanrafif.kicksfilm.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mirfanrafif.kicksfilm.data.repository.MovieRepository
import com.mirfanrafif.kicksfilm.di.Injection
import com.mirfanrafif.kicksfilm.ui.detail.DetailViewModel
import com.mirfanrafif.kicksfilm.ui.favorite.FavoriteViewModel
import com.mirfanrafif.kicksfilm.ui.movies.MoviesViewModel
import com.mirfanrafif.kicksfilm.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val movieRepository: MovieRepository): ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                instance = this
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}