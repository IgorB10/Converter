package com.igor.bykov.converter.presentation.view.mvp

import android.support.annotation.StringRes

/**
 * base MVP view
 */
interface ErrorView {

  fun renderError(@StringRes error: Int)
}