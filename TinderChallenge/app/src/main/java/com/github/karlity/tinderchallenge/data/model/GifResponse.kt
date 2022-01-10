package com.github.karlity.tinderchallenge.data.model

sealed class GifResponse {
 data class Data(
  val gifs : List<Gif>,
  val pagedResponse: PaginationObject
 ) : GifResponse()

  data class ApiException(val errorCode: Int, val errorMessage: String?) : GifResponse()
  data class UnknownException(val throwable: Throwable) : GifResponse()
}