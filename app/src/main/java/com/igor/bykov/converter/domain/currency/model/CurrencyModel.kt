package com.igor.bykov.converter.domain.currency.model

/**
 * Currency business model
 */
data class CurrencyModel(
    val base: String,
    val date: String,
    val rates: Map<String, Float>
    )