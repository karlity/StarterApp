package com.github.karlity.tinderchallenge.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.github.karlity.tinderchallenge.composable.GiphyGrid
import com.github.karlity.tinderchallenge.databinding.SearchLayoutBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChallengeFragment : Fragment() {

  lateinit var binding: SearchLayoutBinding
  private val presenter: ChallengeViewModel by viewModels()

  @ExperimentalAnimationApi
  @ExperimentalFoundationApi
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
    binding = SearchLayoutBinding.inflate(inflater, container, false)

    binding.composeView.apply {
      setContent {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
        GiphyGrid(presenter)
      }
    }
    return binding.root
  }

}