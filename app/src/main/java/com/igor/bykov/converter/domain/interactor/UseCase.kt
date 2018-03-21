package com.igor.bykov.converter.domain.interactor

import io.reactivex.Single

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 */
abstract class UseCase<T, in Params> {

  internal abstract fun buildUseCase(params: Params): Single<T>

  fun get(params: Params): Single<T> {
    return buildUseCase(params)
  }
}
