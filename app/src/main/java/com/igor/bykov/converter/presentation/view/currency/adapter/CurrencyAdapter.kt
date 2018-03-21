package com.igor.bykov.converter.presentation.view.currency.adapter

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.igor.bykov.converter.presentation.view.model.CurrencyViewModel
import com.igor.bykov.converter.presentation.view.model.KEY_RATE


/**
 * Currency Adapter
 */
class CurrencyAdapter(private var items: MutableList<CurrencyViewModel>) :
    RecyclerView.Adapter<CurrencyViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
    return CurrencyViewHolder(parent)
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
    holder.bindModel(items[position])
  }

  override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int,
      payloads: MutableList<Any>) {
    if (payloads.isEmpty()) {
      super.onBindViewHolder(holder, position, payloads)
    } else if(position != 0) {
      val bundle = payloads[0] as Bundle
      if (!bundle.isEmpty) {
        holder.updateUI(bundle.getFloat(KEY_RATE))
      }
    }
  }

  override fun onViewRecycled(holder: CurrencyViewHolder) {
    super.onViewRecycled(holder)
    holder.clear()
  }

  fun updateItems(newItems: MutableList<CurrencyViewModel>, diffResult: DiffUtil.DiffResult?) {
    if(!items.isEmpty()) {
      this.items = newItems
      diffResult?.dispatchUpdatesTo(this)
    } else {
      this.items = newItems
      notifyDataSetChanged()
    }
  }
}