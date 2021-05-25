package com.mirfanrafif.kicksfilm.data.source.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TmdbService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMovieApiService(): MovieApiService = retrofit.create(MovieApiService::class.java)

    fun getTvShowApiService(): TvShowApiService = retrofit.create(TvShowApiService::class.java)


}