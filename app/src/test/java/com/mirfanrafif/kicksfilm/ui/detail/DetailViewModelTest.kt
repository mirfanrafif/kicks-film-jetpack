package com.mirfanrafif.kicksfilm.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mirfanrafif.kicksfilm.data.FilmData
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.data.repository.MovieRepository
import com.mirfanrafif.kicksfilm.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    lateinit var tvObserver: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getDetailMovie() {
        val movie = FilmData.getMovies()[0]
        val dummyMovies = Resource.success(movie)
        val movieList = MutableLiveData<Resource<MovieEntity>>()
        movieList.value = dummyMovies
        Mockito.`when`(movieRepository.getDetailMovie(movie.id)).thenReturn(movieList)

        val movies = viewModel.getDetailMovie(movie.id).value
        verify(movieRepository).getDetailMovie(movie.id)
        assertNotNull(movies)

        viewModel.getDetailMovie(movie.id).observeForever(movieObserver)
        verify(movieObserver).onChanged(movies)
    }

    @Test
    fun getTvShow() {
        val dummyMovie = FilmData.getTVShows()[0]
        val movieResource = Resource.success(dummyMovie)
        val movieList = MutableLiveData<Resource<TvShowEntity>>()
        movieList.value = movieResource
        Mockito.`when`(movieRepository.getDetailTvShow(dummyMovie.id)).thenReturn(movieList)

        val expectedMovie = viewModel.getTvShow(dummyMovie.id).value
        verify(movieRepository).getDetailTvShow(dummyMovie.id)
        assertNotNull(expectedMovie)

        viewModel.getTvShow(dummyMovie.id).observeForever(tvObserver)
        verify(tvObserver).onChanged(expectedMovie)
    }

    @Test
    fun updateMovie() {
        val dummyMovie = FilmData.getMovies()[0]
        viewModel.updateMovie(dummyMovie)
        verify(movieRepository).updateMovie(dummyMovie)
    }

    @Test
    fun updateTvShow() {
        val dummyTvShow = FilmData.getTVShows()[0]
        viewModel.updateTvShow(dummyTvShow)
        verify(movieRepository).updateTvShow(dummyTvShow)
    }
}