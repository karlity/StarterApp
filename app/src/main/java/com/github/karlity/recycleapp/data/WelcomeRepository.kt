package com.github.karlity.recycleapp.data

import com.github.karlity.recycleapp.data.model.BuildingSystem
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject


class WelcomeRepository @Inject constructor(private val recyclingApi: RecyclingApi) {

  suspend fun getBuildingSystems() : Response<BuildingSystem> {
    return recyclingApi.getBuildingSystems()
  }

}