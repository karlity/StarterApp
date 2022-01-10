package com.github.tinderchallenge

import com.github.karlity.tinderchallenge.challenge.ChallengeAction
import com.github.karlity.tinderchallenge.challenge.ChallengeViewModel
import com.github.karlity.tinderchallenge.challenge.ChallengeState
import com.github.karlity.tinderchallenge.data.ChallengeRepository
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test

class ChallengePresenterTests {

  val repository: ChallengeRepository = mockk(relaxed = true)

  fun presenter() = spyk(ChallengeViewModel(repository))


  @get:Rule
  val testCoroutineRule = TestCoroutineRule()


  @InternalCoroutinesApi
  @Test
  fun `Search Giphy`() {

    testCoroutineRule.runBlockingTest {
      val updatedState = ChallengeState(gifList = null)

      runCatching {
        this.launch {
          presenter().postAction(ChallengeAction.Search("hi"))
        }

      presenter().stateFlow.collectLatest {
        assertEquals(updatedState, it)
      }
      }
    }
  }

}



