package com.mirfanrafif.kicksfilm.data.source.remote.api

import com.mirfanrafif.kicksfilm.data.source.remote.responses.TvDetailResponse
import com.mirfanrafif.kicksfilm.data.source.remote.responses.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApiService {
    @GET("3/discover/tv")
    fun discoverTvShows(@Query("api_key")apiKey: String): Call<TvResponse>

    @GET("3/tv/{tv_id}")
    fun getTvShow(@Path("tv_id")tvId: Int, @Query("api_key")apiKey: String): Call<TvDetailResponse>
}