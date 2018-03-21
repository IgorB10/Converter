package com.igor.bykov.converter.presentation.view.utils

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife

/**
 * Helper class that provider useful methods for descendants
 */
abstract class BaseViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView) {

  protected var model: M? = null

  init {
    ButterKnife.bind(this, itemView)
  }

  /**
   * Binds a model in view holder
   * @param model A model for binding
   */
  abstract fun bindModel(model: M)

  companion object {
    fun inflate(parent: ViewGroup, @LayoutRes layoutRes: Int): View {
      return LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
    }
  }
}
