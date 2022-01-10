package com.github.karlity.tinderchallenge.data

import com.giphy.sdk.ui.Giphy
import com.github.karlity.tinderchallenge.data.model.Gif
import com.github.karlity.tinderchallenge.data.model.GiphyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChallengeApi {

  @GET("/v1/gifs/search")
  suspend fun getSearch(
    @Query("q") query: String,
    @Query("limit") pageLimit: Int,
    @Query("offset") pageStart: Int,
    @Query("api_key") apiKey: String,
    ) : Response<GiphyResponse>

  @GET("/v1/gifs/trending")
  suspend fun getTrending(
    @Query("limit") pageLimit: Int,
    @Query("offset") pageStart: Int,
    @Query("api_key") apiKey: String,
  ) : Response<GiphyResponse>
}