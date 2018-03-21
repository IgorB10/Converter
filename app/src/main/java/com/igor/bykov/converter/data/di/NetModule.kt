package com.igor.bykov.converter.data.di

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.igor.bykov.converter.BuildConfig
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Net Module
 */
@Module
abstract class NetModule {

  @Module
  companion object {


    private val HTTP_RESPONSE_CACHE = (10 * 1024 * 1024).toLong()
    private val HTTP_TIMEOUT_S = 30

    @Provides
    @Singleton
    @JvmStatic
    fun provideHttpCache(applicationContext: Context): Cache {
      return Cache(applicationContext.cacheDir, HTTP_RESPONSE_CACHE)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideGson(): Gson {
      val gsonBuilder = GsonBuilder()
      gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      return gsonBuilder.create()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkhttpClient(cache: Cache, logging: HttpLoggingInterceptor): OkHttpClient {
      val client = OkHttpClient.Builder()
          .addInterceptor(logging)
          .connectTimeout(HTTP_TIMEOUT_S.toLong(), TimeUnit.SECONDS)
          .readTimeout(HTTP_TIMEOUT_S.toLong(), TimeUnit.SECONDS)
          .writeTimeout(HTTP_TIMEOUT_S.toLong(), TimeUnit.SECONDS)
          .cache(cache)
      return client.build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
      return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
      val logging = HttpLoggingInterceptor()
      logging.level =
          if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
          else HttpLoggingInterceptor.Level.NONE
      return logging
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient,
        adapterFactory: RxJava2CallAdapterFactory): Retrofit {
      return Retrofit.Builder()
          .baseUrl(BuildConfig.BASE_URL)
          .client(okHttpClient)
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(adapterFactory)
          .build()
    }
  }
}
