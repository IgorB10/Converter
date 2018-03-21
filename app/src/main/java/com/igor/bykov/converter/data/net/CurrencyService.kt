package com.igor.bykov.converter.data.net

import com.igor.bykov.converter.data.currency.entity.CurrencyEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API service for entertainments
 */
interface CurrencyService {

  @GET("latest")
  fun getCurrency(@Query("base") base: String): Single<CurrencyEntity>
}
