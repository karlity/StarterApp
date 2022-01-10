package com.github.karlity.tinderchallenge.api

import androidx.viewbinding.BuildConfig
import com.github.karlity.tinderchallenge.data.ChallengeApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {


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
      .baseUrl("https://api.giphy.com")
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

  @Singleton
  @Provides
  fun createChallengeApi(
    retrofit: Retrofit
  ) : ChallengeApi {
    return retrofit.create(ChallengeApi::class.java)
  }
}