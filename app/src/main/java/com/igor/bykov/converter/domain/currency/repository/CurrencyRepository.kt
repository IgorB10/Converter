package com.igor.bykov.converter.domain.currency.repository

import com.igor.bykov.converter.domain.currency.model.CurrencyModel
import io.reactivex.Single

/**
 * Currency repository interface
 */
interface CurrencyRepository {

  /**
   * Get latest currency list
    */
  fun getCurrrency(baseCurrency: String): Single<CurrencyModel>
}