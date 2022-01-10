package com.github.karlity.tinderchallenge.challenge


sealed class ChallengeAction {
    object Load : ChallengeAction()
    data class NextPage(val currentPage: Int, val nextPage:Int): ChallengeAction()
    data class Search(val searchQuery: String) : ChallengeAction()
}