package com.github.karlity.tinderchallenge.challenge

import com.github.karlity.tinderchallenge.data.model.Gif

data class ChallengeState(
  val isLoading: Boolean = false,
  val isError: Boolean = false,
  val gifList: List<Gif>? = null
  )