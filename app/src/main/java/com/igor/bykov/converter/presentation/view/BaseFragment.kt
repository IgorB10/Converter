package com.igor.bykov.converter.presentation.view

import android.arch.lifecycle.LifecycleObserver
import android.content.Context
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * Base fragment class
 */
abstract class BaseFragment : Fragment() {

  @Inject lateinit var eventBus: EventBus

  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onStart() {
    super.onStart()
    lifecycle.addObserver(bindLifeCycle())
    eventBus.register(this)
  }

  override fun onStop() {
    super.onStop()
    eventBus.unregister(this)
  }

  abstract fun setUp()


  abstract fun bindLifeCycle() : LifecycleObserver
}