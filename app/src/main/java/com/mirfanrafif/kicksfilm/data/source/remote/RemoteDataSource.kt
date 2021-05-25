package com.mirfanrafif.kicksfilm.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mirfanrafif.kicksfilm.utils.EspressoIdlingResource
import com.mirfanrafif.kicksfilm.data.source.remote.api.TmdbService
import com.mirfanrafif.kicksfilm.data.source.remote.responses.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val tmdbService: TmdbService){
    companion object {
        private var TAG = RemoteDataSource::class.java.simpleName
        private const val API_KEY = "77aae75f4c0a1788862312068fc792f0"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(tmdbService: TmdbService): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource(tmdbService).apply {
                instance = this
            }
        }
    }

    fun discoverMovies(): LiveData<ApiResponse<List<MovieItem>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieItem>>>()
        tmdbService.getMovieApiService().discoverMovies(API_KEY).enqueue(object :
            Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        resultMovie.value = ApiResponse.success(data.results)
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "Error: ${t.message}")
            }

        })
        return resultMovie
    }

    fun discoverTvShow() : LiveData<ApiResponse<List<TvShowItem>>>{
        val listTvShow = MutableLiveData<ApiResponse<List<TvShowItem>>>()
        EspressoIdlingResource.increment()
        tmdbService.getTvShowApiService().discoverTvShows(API_KEY).enqueue(object :
            Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                if (response.isSuccessful) {
                    val tvShowList = response.body()
                    if (tvShowList != null) {
                        listTvShow.value = ApiResponse.success(tvShowList.results)
                        EspressoIdlingResource.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e(TAG, "Error: ${t.message}")
            }

        })
        return listTvShow
    }

    fun getMovie(id: Int): LiveData<ApiResponse<MovieDetailResponse>>{
        val movie = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        EspressoIdlingResource.increment()
        tmdbService.getMovieApiService().getMovie(id, API_KEY).enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        EspressoIdlingResource.decrement()
                        movie.value = ApiResponse.success(data)
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e(TAG, "Error: ${t.message}")
            }

        })
        return movie
    }

    fun getTvShow(id: Int): MutableLiveData<ApiResponse<TvDetailResponse>>{
        EspressoIdlingResource.increment()
        val tvShowDetailResponse = MutableLiveData<ApiResponse<TvDetailResponse>>()
        tmdbService.getTvShowApiService().getTvShow(id, API_KEY).enqueue(object : Callback<TvDetailResponse> {
            override fun onResponse(
                call: Call<TvDetailResponse>,
                response: Response<TvDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        EspressoIdlingResource.decrement()
                        tvShowDetailResponse.value = ApiResponse.success(data)
                    }
                }
            }

            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                Log.e(TAG, "Error: ${t.message}")
            }

        })
        return tvShowDetailResponse
    }
}