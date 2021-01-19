package com.github.karlity.recycleapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BuildingSystem(
  val id: Long,
  val name: String,
  @Json(name = "full_name") val fullName: String,
  val slug: String
)
