package com.igor.bykov.converter.data.currency.entity

/**
 * Currency business model
 */
data class CurrencyEntity(
    val base: String,
    val date: String,
    val rates: Map<String, Float>
    )