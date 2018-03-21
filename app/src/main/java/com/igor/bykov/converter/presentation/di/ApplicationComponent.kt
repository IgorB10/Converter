package com.igor.bykov.converter.presentation.di

import com.igor.bykov.converter.BaseApplication
import com.igor.bykov.converter.data.di.ApiModule
import com.igor.bykov.converter.data.di.NetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Application Component
 */
@Singleton
@Component(modules = [
      ApplicationModule::class,
      AndroidInjectionModule::class,
      ActivityBuilder::class,
      DataModule::class,
      NetModule::class,
      ApiModule::class
])
interface ApplicationComponent {

  fun inject(application: BaseApplication)

  @Component.Builder
  interface Builder {

    @BindsInstance fun application(application: BaseApplication): Builder
    fun build(): ApplicationComponent
  }
}