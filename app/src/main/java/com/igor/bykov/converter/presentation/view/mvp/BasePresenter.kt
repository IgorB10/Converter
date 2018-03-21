package com.igor.bykov.converter.presentation.view.mvp

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Base MVP Presenter
 */
abstract class BasePresenter<T : MvpView> : MvpView {

  /**
   * Collect subscriptions
   */
  protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

  protected lateinit var view: T

  fun bind(view: T) {
    this.view = view
  }

  /**
   * Clear composite after stop
   */
  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  fun connectListener() {
    compositeDisposable.clear()
  }

  /**
   * We are using RxJava transformers to compose the observables in order
   * to not break the chain and help in the UI to do process and avoid
   * lagging
   */
  private fun <T> applySchedulers(): ObservableTransformer<T, T> {
    return ObservableTransformer {
      it.subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
    }
  }

  /**
   * Bind stream to lifecycle
   */
  protected fun <T> bindLifecycle(single: Observable<T>, consumer: Consumer<T>,
      consumerError: Consumer<in Throwable>) {
    compositeDisposable.add(single.compose(applySchedulers()).subscribe(consumer, consumerError))
  }
}