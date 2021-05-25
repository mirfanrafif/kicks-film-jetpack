package com.mirfanrafif.kicksfilm.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mirfanrafif.kicksfilm.data.FilmData
import com.mirfanrafif.kicksfilm.data.repository.MovieRepository
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.utils.PagedListUtil
import com.mirfanrafif.kicksfilm.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getAllTvShow() {
        val pagedMovies = PagedListUtil.mockPagedList(FilmData.getTVShows())
        val dummyMovies = Resource.success(pagedMovies)
        val movieList = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        movieList.value = dummyMovies
        Mockito.`when`(movieRepository.getAllTvShows()).thenReturn(movieList)

        val movies = viewModel.getAllTvShow().value
        verify(movieRepository).getAllTvShows()
        assertNotNull(movies)

        viewModel.getAllTvShow().observeForever(observer)
        verify(observer).onChanged(movieList.value)
    }
}