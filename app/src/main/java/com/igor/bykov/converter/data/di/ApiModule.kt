package com.igor.bykov.converter.data.di

import com.igor.bykov.converter.data.net.CurrencyService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Module for api
 */
@Module
abstract class ApiModule {

  @Module
  companion object {

    @Provides
    @JvmStatic
    @Singleton
    fun provideCurrencyService(retrofit: Retrofit): CurrencyService {
      return retrofit.create(CurrencyService::class.java)
    }
  }

}
