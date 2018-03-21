package com.igor.bykov.converter.presentation.di

import com.igor.bykov.converter.data.currency.repository.CurrencyDataStoreRepository
import com.igor.bykov.converter.data.net.CurrencyService
import com.igor.bykov.converter.domain.currency.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Application module
 */
@Module
abstract class DataModule {

  @Module
  companion object {

    @Provides
    @JvmStatic
    @Singleton
    fun provideCurrencyRepository(currencyService: CurrencyService): CurrencyRepository {
      return CurrencyDataStoreRepository(currencyService)
    }
  }
}