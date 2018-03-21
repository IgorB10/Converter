package com.igor.bykov.converter.presentation.view.currency

import android.support.v7.util.DiffUtil
import com.igor.bykov.converter.R
import com.igor.bykov.converter.domain.currency.interactor.GetCurrency
import com.igor.bykov.converter.presentation.view.currency.adapter.CurrencyDiffCallback
import com.igor.bykov.converter.presentation.view.model.CurrencyViewModel
import com.igor.bykov.converter.presentation.view.model.mapper.CurrencyMapper
import com.igor.bykov.converter.presentation.view.mvp.BasePresenter
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.BehaviorSubject
import java.util.Collections
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Presenter for [CurrencyListFragment]
 */
class CurrencyPresenter @Inject constructor(
    private val getCurrency: GetCurrency
) : BasePresenter<CurrencyView>() {

  private val currencyUpdate = BehaviorSubject.create<CurrencyChangeRequest>()
  private var currencyRateDefault = CurrencyChangeRequest("AUD", 100F)
  private var initialPair = Pair<MutableList<CurrencyViewModel>, DiffUtil.DiffResult?>(
      Collections.emptyList(),
      null)

  init {
    bindLifecycle(currencyUpdate.debounce(100, TimeUnit.MILLISECONDS)
        .switchMapSingle { rate ->
          getCurrency.get(rate.base).map { CurrencyMapper.transform(it, rate.base, rate.rate) }
        }.scan(initialPair, { oldPair, next ->
          val myDiff = CurrencyDiffCallback(oldPair.first, next)
          val diffResult = DiffUtil.calculateDiff(myDiff, true)
          return@scan Pair(next, diffResult)
        }),
        Consumer { view.render(it.first, it.second) },
        Consumer { view.renderError(R.string.error) })

    //900 MILLISECONDS to de bounce quick selection
    bindLifecycle(Observable.interval(900, TimeUnit.MILLISECONDS),
        Consumer { currencyUpdate.onNext(currencyRateDefault) },
        Consumer { view.renderError(R.string.error) })
  }

  fun loadCurrency(base: String, rate: Float) {
    this.currencyRateDefault = CurrencyChangeRequest(base, rate)
    currencyUpdate.onNext(currencyRateDefault)
  }

  /**
   * Request currency model
   */
  data class CurrencyChangeRequest(var base: String, val rate: Float)
}