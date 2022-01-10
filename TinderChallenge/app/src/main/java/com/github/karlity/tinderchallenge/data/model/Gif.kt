package com.github.karlity.tinderchallenge.data.model

import com.squareup.moshi.JsonClass

data class Gif(
  val type: String = "gif",
  val id: String?,
  val slug: String?,
  val url: String?,
  val bitlyUrl: String?,
  val embedUrl: String?,
  val userName: String?,
  val source: String?,
  val rating: String?,
  val contentUrl: String? = null,
  val sourceTld: String? = null,
  val sourcePostUrl: String? = null,
  val updateDatetime: String? = null,
  val createDateTime: String? = null,
  val title: String? = null
)
