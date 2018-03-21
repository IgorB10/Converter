package com.igor.bykov.converter.data.currency.repository

import com.igor.bykov.converter.data.net.CurrencyService
import com.igor.bykov.converter.domain.currency.model.CurrencyModel
import com.igor.bykov.converter.domain.currency.repository.CurrencyRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Igor Bykov on 3/20/18.
 */
@Singleton
class CurrencyDataStoreRepository @Inject constructor(
    private val currencyService: CurrencyService
): CurrencyRepository {

  override fun getCurrrency(baseCurrency: String): Single<CurrencyModel> {
    return currencyService.getCurrency(baseCurrency).map { CurrencyModel(it.base, it.date, it.rates) }
  }
}