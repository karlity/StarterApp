package com.github.karlity.recycleapp.api

import com.github.karlity.recycleapp.BuildConfig
import com.github.karlity.recycleapp.data.RecyclingApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.addAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

  companion object {
   private const val host = "https://api.gsa.gov"
  }

  @Singleton
  @Provides
  fun createMoshi() : Moshi {
    return Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()
  }

  @Singleton
  @Provides
  fun createHttpLoggingInterceptor() : HttpLoggingInterceptor {
    val logger = object : HttpLoggingInterceptor.Logger {
      override fun log(message: String) {
        Timber.tag("OkHttp").v(message)
      }
    }
    return HttpLoggingInterceptor(logger).apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  @Singleton
  @Provides
  fun createOkHttp(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
    val builder = OkHttpClient.Builder()
    if(BuildConfig.DEBUG) builder.addInterceptor(loggingInterceptor)
    return builder.build()
  }

  @Singleton
  @Provides
  fun createRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi
  ): Retrofit {
    return Retrofit.Builder()
      .baseUrl(host)
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

  @Singleton
  @Provides
  fun createRecyclingApi(
    retrofit: Retrofit
  ) : RecyclingApi {
    return retrofit.create(RecyclingApi::class.java)
  }
}