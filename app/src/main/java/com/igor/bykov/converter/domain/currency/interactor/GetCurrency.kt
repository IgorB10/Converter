package com.igor.bykov.converter.domain.currency.interactor

import com.igor.bykov.converter.domain.currency.model.CurrencyModel
import com.igor.bykov.converter.domain.currency.repository.CurrencyRepository
import com.igor.bykov.converter.domain.interactor.UseCase
import io.reactivex.Single
import javax.inject.Inject

/**
 * Use case to get currency
 *
 * @param currencyRepository [CurrencyRepository] repository for currency
 */
class GetCurrency @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : UseCase<CurrencyModel, String>() {

  override fun buildUseCase(params: String): Single<CurrencyModel> {
    return currencyRepository.getCurrrency(params)
  }
}