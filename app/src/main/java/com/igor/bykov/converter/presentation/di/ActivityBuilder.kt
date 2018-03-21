package com.igor.bykov.converter.presentation.di

import com.igor.bykov.converter.presentation.view.currency.CurrencyListActivity
import com.igor.bykov.converter.presentation.view.currency.CurrencyListFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * DI Activity Builder
 */
@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = [CurrencyListFragmentProvider::class])
  internal abstract fun bindCurrencyListActivity(): CurrencyListActivity
}