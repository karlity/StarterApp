package com.github.karlity.tinderchallenge.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.giphy.sdk.ui.Giphy
import com.giphy.sdk.ui.utils.VideoCache
import com.github.karlity.tinderchallenge.R
import com.github.karlity.tinderchallenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Timber.plant(Timber.DebugTree())

    Timber.i("onCreate")

    setContentView(R.layout.activity_main)
  }
}