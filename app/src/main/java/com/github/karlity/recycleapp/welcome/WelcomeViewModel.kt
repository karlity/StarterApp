package com.github.karlity.recycleapp.welcome

import androidx.lifecycle.ViewModel
import com.github.karlity.recycleapp.data.WelcomeRepository
import javax.inject.Inject

class WelcomePresenter @Inject constructor(private val welcomeRepo: WelcomeRepository) : ViewModel() {
  
}