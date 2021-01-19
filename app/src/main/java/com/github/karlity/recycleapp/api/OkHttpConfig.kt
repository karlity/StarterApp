package com.github.karlity.recycleapp.api

class OkHttpConfig(
  val host: String,
  val key: String
) {
  companion object {
    val PRD = OkHttpConfig(
      host = "https://api.gsa.gov",
      key = "VnneH7AKpNkeRGIpjvJPGWWph3qcPhU8HJYYfEU0"
    )
  }
}