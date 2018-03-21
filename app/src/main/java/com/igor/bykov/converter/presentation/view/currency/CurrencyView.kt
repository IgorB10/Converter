package com.igor.bykov.converter.presentation.view.currency

import android.support.v7.util.DiffUtil
import com.igor.bykov.converter.presentation.view.model.CurrencyViewModel
import com.igor.bykov.converter.presentation.view.mvp.ErrorView
import com.igor.bykov.converter.presentation.view.mvp.MvpView

/**
 * View contract for [CurrencyListFragment]
 */
interface CurrencyView : MvpView, ErrorView {

  fun render(currencyModel: MutableList<CurrencyViewModel>, diffResult: DiffUtil.DiffResult?)
}