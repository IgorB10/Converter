package com.igor.bykov.converter.presentation.view.model

/**
 * Currency View Model
 */
const val KEY_RATE = "rate"

data class CurrencyViewModel(val name: String, var rate: Float)