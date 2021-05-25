package com.mirfanrafif.kicksfilm.data.source.remote.api

import com.mirfanrafif.kicksfilm.data.source.remote.responses.MovieDetailResponse
import com.mirfanrafif.kicksfilm.data.source.remote.responses.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("3/discover/movie")
    fun discoverMovies(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("3/movie/{movie_id}")
    fun getMovie( @Path("movie_id") movieId: Int, @Query("api_key") apiKey: String
                ): Call<MovieDetailResponse>
}