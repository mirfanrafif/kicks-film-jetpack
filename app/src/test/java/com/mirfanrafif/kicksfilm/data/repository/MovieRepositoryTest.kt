package com.mirfanrafif.kicksfilm.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mirfanrafif.kicksfilm.data.FilmData
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.data.source.local.LocalDataSource
import com.mirfanrafif.kicksfilm.data.source.remote.RemoteDataSource
import com.mirfanrafif.kicksfilm.utils.AppExecutor
import com.mirfanrafif.kicksfilm.utils.LiveDataTestUtil
import com.mirfanrafif.kicksfilm.utils.PagedListUtil
import com.mirfanrafif.kicksfilm.vo.Resource

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.logging.Logger

@RunWith(MockitoJUnitRunner::class)
@Suppress("UNCHECKED_CAST")
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private var local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutor::class.java)

    private val movieResponses = FilmData.getMovies()
    private val selectedMovie = movieResponses[0]
    private val movieRepository = FakeMovieRepository(local, remote, appExecutors)

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        movieRepository.getAllMovies()

        val movie = Resource.success(PagedListUtil.mockPagedList(FilmData.getMovies()))
        verify(local).getAllMovies()
        assertNotNull(movie)
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShow()).thenReturn(dataSourceFactory)
        movieRepository.getAllTvShows()

        val tvShows = Resource.success(PagedListUtil.mockPagedList(FilmData.getTVShows()))
        verify(local).getAllTvShow()
        assertNotNull(tvShows)
    }

    @Test
    fun getDetailMovie() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = selectedMovie
        `when`(local.getMoviesById(selectedMovie.id)).thenReturn(dummyMovie)

        val expected = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(selectedMovie.id))
        verify(local).getMoviesById(selectedMovie.id)
        assertNotNull(expected)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovies()

        verify(local).getFavoriteMovies()
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteTvShows()

        verify(local).getFavoriteTvShow()
    }

    @Test
    fun updateMovie() {

        val movie = selectedMovie
        movie.isFavorite = true

        doNothing().`when`(local).updateMovie(movie)
    }

    @Test
    fun updateTvShow() {
        val tvShowEntity = FilmData.getTVShows()[0]
        tvShowEntity.isFavorite = true

        doNothing().`when`(local).updateTvShow(tvShowEntity)
    }
}