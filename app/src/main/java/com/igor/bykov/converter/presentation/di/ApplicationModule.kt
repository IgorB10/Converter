package com.igor.bykov.converter.presentation.di

import android.content.Context
import com.igor.bykov.converter.BaseApplication
import com.igor.bykov.converter.ConverterEventBusIndex
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

/**
 * Application module
 */
@Module
class ApplicationModule {

  @Provides
  @Singleton
  fun provideApplicationContext(application: BaseApplication): Context {
    return application
  }

  @Provides
  @Singleton
  fun provideEventBus(): EventBus {
    EventBus.builder().addIndex(ConverterEventBusIndex()).installDefaultEventBus()
    return EventBus.getDefault()
  }

}