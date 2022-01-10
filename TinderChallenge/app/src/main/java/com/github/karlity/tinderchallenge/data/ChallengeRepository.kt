package com.github.karlity.tinderchallenge.data

import com.github.karlity.tinderchallenge.data.model.Gif
import com.github.karlity.tinderchallenge.data.model.GifResponse
import com.github.karlity.tinderchallenge.data.model.GiphyResponse
import retrofit2.Response
import javax.inject.Inject


class ChallengeRepository @Inject constructor(private val challengeApi: ChallengeApi) {

  companion object {
    val API_KEY = "aIijUf5OCrqITjS6YjCY4gguTL3je6Ko"
  }

  suspend fun getSearchResults(pageStart: Int, pageLimit: Int, query: String) : GifResponse {
    return try {
      val response: Response<GiphyResponse> = challengeApi.getSearch(apiKey = API_KEY, pageStart = pageStart, pageLimit = pageLimit, query = query)
      if (response.isSuccessful) {
        val body = checkNotNull(response.body())
        GifResponse.Data(gifs = body.data, pagedResponse = body.pagination)
      } else {
        GifResponse.ApiException(
          errorCode = response.code(),
          errorMessage = response.errorBody()?.toString()
        )
      }
    }catch(throwable: Throwable){
      GifResponse.UnknownException(throwable)
    }
  }

  suspend fun getTrendingResults(pageStart: Int, pageLimit: Int) : GifResponse {
    return try {
      val response: Response<GiphyResponse> = challengeApi.getTrending(apiKey = API_KEY, pageStart = pageStart, pageLimit = pageLimit)
      if (response.isSuccessful) {
        val body = checkNotNull(response.body())
        GifResponse.Data(gifs = body.data, pagedResponse = body.pagination)
      } else {
        GifResponse.ApiException(
          errorCode = response.code(),
          errorMessage = response.errorBody()?.toString()
        )
      }
    }catch(throwable: Throwable){
      GifResponse.UnknownException(throwable)
    }
  }

}