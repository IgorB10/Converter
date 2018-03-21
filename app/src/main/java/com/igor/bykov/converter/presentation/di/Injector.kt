package com.igor.bykov.converter.presentation.di

import com.igor.bykov.converter.BaseApplication
import com.igor.bykov.converter.BuildConfig
import com.igor.bykov.converter.data.di.NetModule

/**
 * Used to inject classes in dagger graph
 */
enum class Injector {

  INSTANCE;

  private lateinit var applicationComponent: ApplicationComponent

  fun initialise(application: BaseApplication) {
    applicationComponent = DaggerApplicationComponent
        .builder()
        .application(application)
        .build()
    applicationComponent.inject(application)
  }

}