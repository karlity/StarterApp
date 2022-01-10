package com.github.tinderchallenge

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import kotlin.jvm.Throws

class TestCoroutineRule : TestRule {
  private val testDispatcher = TestCoroutineDispatcher()
  private val testCoroutineScope = TestCoroutineScope(testDispatcher)

  override fun apply(base: Statement, description: Description?)= object : Statement() {
    @Throws(Throwable::class)
    override fun evaluate() {
      Dispatchers.setMain(testDispatcher)

      base.evaluate()

      Dispatchers.resetMain()
      testCoroutineScope.cleanupTestCoroutines()
    }
  }
  fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
    testCoroutineScope.runBlockingTest { block() }
}