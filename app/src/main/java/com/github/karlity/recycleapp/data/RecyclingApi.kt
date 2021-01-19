package com.github.karlity.recycleapp.data

import com.github.karlity.recycleapp.data.model.BuildingSystem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecyclingApi {
  companion object {
    private const val key = "VnneH7AKpNkeRGIpjvJPGWWph3qcPhU8HJYYfEU0"
  }

  @GET("/sustainability/sftool/v1/building-systems")
  suspend fun getBuildingSystems(
    @Query("api_key") apiKey: String = key
  ) : Response<BuildingSystem>
}