package com.igor.bykov.converter.presentation.view.currency

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * DI provide for [CurrencyListFragment]
 */
@Module
abstract class CurrencyListFragmentProvider {

  @ContributesAndroidInjector
  internal abstract fun provideCurrencyListFragment(): CurrencyListFragment
}