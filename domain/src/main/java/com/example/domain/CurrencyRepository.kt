package com.example.domain

import com.example.domain.model.Currency
import io.reactivex.Single

interface CurrencyRepository {
    fun getCurrencies(date: String) : Single<List<Currency>>
}