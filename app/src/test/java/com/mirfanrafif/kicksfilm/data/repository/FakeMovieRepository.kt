package com.mirfanrafif.kicksfilm.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mirfanrafif.kicksfilm.data.NetworkBoundResource
import com.mirfanrafif.kicksfilm.data.entities.MovieEntity
import com.mirfanrafif.kicksfilm.data.entities.TvShowEntity
import com.mirfanrafif.kicksfilm.data.source.local.LocalDataSource
import com.mirfanrafif.kicksfilm.data.source.remote.ApiResponse
import com.mirfanrafif.kicksfilm.data.source.remote.RemoteDataSource
import com.mirfanrafif.kicksfilm.data.source.remote.responses.MovieDetailResponse
import com.mirfanrafif.kicksfilm.data.source.remote.responses.MovieItem
import com.mirfanrafif.kicksfilm.data.source.remote.responses.TvDetailResponse
import com.mirfanrafif.kicksfilm.data.source.remote.responses.TvShowItem
import com.mirfanrafif.kicksfilm.utils.AppExecutor
import com.mirfanrafif.kicksfilm.vo.Resource

class FakeMovieRepository constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutor: AppExecutor
): IMovieRepository{
    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieItem>>(appExecutor) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(3)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> {
                return remoteDataSource.discoverMovies()
            }

            override fun saveCallResult(data: List<MovieItem>) {
                val movieList = data.map { result ->
                    val releaseDate = result.releaseDate.split("-").toTypedArray()
                    MovieEntity(
                        result.id,
                        result.title,
                        releaseDate[0].toInt(),
                        result.overview,
                        result.voteAverage,
                        null,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2${result.backdropPath}")
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowItem>>(appExecutor) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(6)
                    .setPageSize(3)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowItem>>> {
                return remoteDataSource.discoverTvShow()
            }

            override fun saveCallResult(data: List<TvShowItem>) {
                val tvShowList = data.map {
                    val releaseDate = it.firstAirDate.split("-").toTypedArray()
                    TvShowEntity(
                        it.id,
                        it.name,
                        releaseDate[0].toInt(),
                        it.overview,
                        it.voteAverage,
                        null,
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2${it.backdropPath}")
                }
                localDataSource.insertTvShow(tvShowList)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutor) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMoviesById(id)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data?.category == null
            }

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getMovie(id)
            }

            override fun saveCallResult(data: MovieDetailResponse) {
                val releaseDate = data.releaseDate.split("-").toTypedArray()
                val categories = data.genres.joinToString() { it.name }
                val movie = MovieEntity(
                    data.id,
                    data.title,
                    releaseDate[0].toInt(),
                    data.overview,
                    data.voteAverage,
                    categories,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2${data.backdropPath}")
                localDataSource.updateMovie(movie)
            }

        }.asLiveData()
    }

    override fun getDetailTvShow(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvDetailResponse>(appExecutor) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localDataSource.getTvShowById(id)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data?.category == null
            }

            override fun createCall(): LiveData<ApiResponse<TvDetailResponse>> {
                return remoteDataSource.getTvShow(id)
            }

            override fun saveCallResult(data: TvDetailResponse) {
                val releaseDate = data.firstAirDate.split("-").toTypedArray()
                val categories = data.genres.joinToString() { it.name }
                val tvShowEntity = TvShowEntity(
                    data.id,
                    data.name,
                    releaseDate[0].toInt(),
                    data.overview,
                    data.voteAverage,
                    categories,
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2${data.backdropPath}")
                localDataSource.updateTvShow(tvShowEntity)
            }

        }.asLiveData()
    }

    override fun updateMovie(movieEntity: MovieEntity) {
        appExecutor.diskIO().execute { localDataSource.updateMovie(movieEntity) }
    }

    override fun updateTvShow(tvShowEntity: TvShowEntity) {
        appExecutor.diskIO().execute { localDataSource.updateTvShow(tvShowEntity) }
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), config).build()
    }


}