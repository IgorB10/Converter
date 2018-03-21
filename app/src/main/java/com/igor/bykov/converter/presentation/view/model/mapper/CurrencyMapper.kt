package com.igor.bykov.converter.presentation.view.model.mapper

import com.igor.bykov.converter.domain.currency.model.CurrencyModel
import com.igor.bykov.converter.presentation.view.model.CurrencyViewModel
import java.math.BigDecimal

/**
 * Mapper from [CurrencyModel]
 */
class CurrencyMapper {

  companion object {
    fun transform(currencyModel: CurrencyModel, base: String, range: Float): MutableList<CurrencyViewModel> {
      val list = ArrayList<CurrencyViewModel>(currencyModel.rates.size)
      list.add(CurrencyViewModel(base, range))
      for (item in currencyModel.rates) {
        val value = (item.value * range).toBigDecimal().setScale(2, BigDecimal.ROUND_HALF_UP).toFloat()
        list.add(CurrencyViewModel(item.key, value))
      }
      return list
    }
  }
}