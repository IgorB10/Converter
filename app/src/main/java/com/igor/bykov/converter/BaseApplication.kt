package com.igor.bykov.converter

import android.app.Activity
import android.os.StrictMode
import android.support.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.igor.bykov.converter.data.net.ImageInterceptor
import com.igor.bykov.converter.presentation.di.Injector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import okhttp3.OkHttpClient
import javax.inject.Inject


/**
 * Base application
 */
class BaseApplication : MultiDexApplication(), HasActivityInjector {

  @Inject lateinit var androidActivityInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    if (BuildConfig.DEBUG) {
      StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
          .detectNetwork()
          .penaltyLog()
          .build())
      StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
          .detectLeakedClosableObjects()
          .penaltyLog()
          .penaltyDeath()
          .build())
    }
    super.onCreate()
    Injector.INSTANCE.initialise(this)
    Fabric.with(this, Crashlytics())
    this.initFreco()
  }

  private fun initFreco() {
    val builder = OkHttpClient.Builder().addInterceptor(ImageInterceptor())
    val config = OkHttpImagePipelineConfigFactory
        .newBuilder(this, builder.build())
        .build()
    Fresco.initialize(this, config)
  }

  override fun activityInjector() = androidActivityInjector
}