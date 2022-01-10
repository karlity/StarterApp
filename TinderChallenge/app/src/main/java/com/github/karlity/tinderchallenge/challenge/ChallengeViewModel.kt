package com.github.karlity.tinderchallenge.challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.karlity.tinderchallenge.data.ChallengeRepository
import com.github.karlity.tinderchallenge.data.model.GifResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber

@HiltViewModel
class ChallengeViewModel @Inject constructor(private val challengeRepo: ChallengeRepository) : ViewModel() {

  val actionFlow: MutableSharedFlow<ChallengeAction> = MutableSharedFlow(extraBufferCapacity = 10)
  val stateFlow: MutableStateFlow<ChallengeState> = MutableStateFlow(ChallengeState())

   fun postAction(action: ChallengeAction) {
       Timber.wtf("action: $action")
    when(action) {is ChallengeAction.Load -> viewModelScope.launch { getTrending() }
        is ChallengeAction.Search -> viewModelScope.launch{ search(action.searchQuery)}
    }
  }

    private suspend fun search(query: String){
        stateFlow.emit(stateFlow.value.copy(isLoading = true, gifList = null))
        val results = challengeRepo.getSearchResults(0, pageLimit = 10, query = query)

        Timber.wtf("wtf results: $results")
        when (results) {
            is GifResponse.Data -> {
                stateFlow.emit(stateFlow.value.copy(gifList = results.gifs, isLoading = false, isError = false))
            }
            is GifResponse.ApiException -> {
                stateFlow.emit(stateFlow.value.copy(isError = true, isLoading = false))
            }
            is GifResponse.UnknownException -> {
                stateFlow.emit(stateFlow.value.copy(isError = true, isLoading = false))
            }
        }

    }

    private suspend fun getTrending() {
        stateFlow.emit(stateFlow.value.copy(isLoading = true, gifList = null))
        val results = challengeRepo.getTrendingResults(0, pageLimit = 25)

        Timber.wtf("wtf results: $results")

        when (results) {
            is GifResponse.Data -> {
                stateFlow.emit(stateFlow.value.copy(gifList = results.gifs, isLoading = false, isError = false))
            }
            is GifResponse.ApiException -> {
                stateFlow.emit(stateFlow.value.copy(isError = true, isLoading = false))
            }
            is GifResponse.UnknownException -> {
                stateFlow.emit(stateFlow.value.copy(isError = true, isLoading = false))
            }
        }
    }
}