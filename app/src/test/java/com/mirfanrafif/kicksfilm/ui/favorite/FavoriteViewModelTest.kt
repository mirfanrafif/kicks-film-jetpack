package com.mirfanrafif.kicksfilm.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mirfanrafif.kicksfilm.data.FilmData
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.data.repository.MovieRepository
import com.mirfanrafif.kicksfilm.ui.movies.MoviesViewModel
import com.mirfanrafif.kicksfilm.utils.PagedListUtil
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
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var tvObserver: Observer<PagedList<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val pagedMovies = PagedListUtil.mockPagedList(FilmData.getMovies())
        val movieList = MutableLiveData<PagedList<MovieEntity>>()
        movieList.value = pagedMovies
        Mockito.`when`(movieRepository.getFavoriteMovies()).thenReturn(movieList)

        val movies = viewModel.getFavoriteMovies().value
        verify(movieRepository).getFavoriteMovies()
        assertNotNull(movies)

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(movieList.value)
    }

    @Test
    fun getFavoriteTvShows() {
        val pagedMovies = PagedListUtil.mockPagedList(FilmData.getTVShows())
        val movieList = MutableLiveData<PagedList<TvShowEntity>>()
        movieList.value = pagedMovies
        Mockito.`when`(movieRepository.getFavoriteTvShows()).thenReturn(movieList)

        val movies = viewModel.getFavoriteTvShows().value
        verify(movieRepository).getFavoriteTvShows()
        assertNotNull(movies)

        viewModel.getFavoriteTvShows().observeForever(tvObserver)
        verify(tvObserver).onChanged(movieList.value)
    }
}