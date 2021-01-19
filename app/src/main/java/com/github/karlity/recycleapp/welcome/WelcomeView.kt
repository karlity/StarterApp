package com.github.karlity.recycleapp.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.karlity.recycleapp.databinding.WelcomeFragmentBinding
import javax.inject.Inject

class WelcomeView @Inject constructor() {

  lateinit var binding: WelcomeFragmentBinding

   fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
    binding = WelcomeFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }
}