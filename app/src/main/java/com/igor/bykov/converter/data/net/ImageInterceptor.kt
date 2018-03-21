package com.igor.bykov.converter.data.net

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Image flag interceptor
 */
const val BASE_URL = "https://raw.githubusercontent.com/transferwise/currency-flags/master/src/flags/"

class ImageInterceptor: Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val currentRequest = chain.request()
    val newRequest = currentRequest.newBuilder().url(BASE_URL.plus(currentRequest.url().host()).plus(".png"))
    return chain.proceed(newRequest.build())
  }
}