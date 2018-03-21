package com.igor.bykov.converter.presentation.view.currency.adapter

import android.os.Bundle
import android.support.v7.util.DiffUtil
import com.igor.bykov.converter.presentation.view.model.CurrencyViewModel
import com.igor.bykov.converter.presentation.view.model.KEY_RATE


/**
 * Diff currency collection
 * We will ignore first base rate
 */
class CurrencyDiffCallback(
    private val oldCurrency: MutableList<CurrencyViewModel>,
    private val newCurrency: MutableList<CurrencyViewModel>
) : DiffUtil.Callback() {

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldCurrency[oldItemPosition].name == newCurrency[newItemPosition].name
  }

  override fun getOldListSize(): Int {
    return oldCurrency.size
  }

  override fun getNewListSize(): Int {
    return newCurrency.size
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldCurrency[oldItemPosition] == newCurrency[newItemPosition]
  }

  override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
    val oldCurrency = oldCurrency[oldItemPosition]
    val newCurrency = newCurrency[newItemPosition]
    val diffBundle = Bundle()
    if (oldCurrency.rate != newCurrency.rate) {
      diffBundle.putFloat(KEY_RATE, newCurrency.rate)
    }
    return diffBundle
  }
}