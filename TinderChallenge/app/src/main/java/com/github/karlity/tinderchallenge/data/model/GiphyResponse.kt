package com.github.karlity.tinderchallenge.data.model

data class GiphyResponse(
    val data: List<Gif>,
    val pagination: PaginationObject
)
