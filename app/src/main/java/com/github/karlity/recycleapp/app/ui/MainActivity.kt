package com.github.karlity.recycleapp.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.karlity.recycleapp.R
import com.github.karlity.recycleapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Timber.i("onCreate")

    setContentView(R.layout.activity_main)
  }


}