package com.example.data.repository

import com.example.data.CurrencyRetrofitService
import com.example.data.toCurrency
import com.example.domain.model.Currency
import com.example.domain.CurrencyRepository
import io.reactivex.Single

class CurrencyRepositoryImpl(
    private val currencyRetrofitService: CurrencyRetrofitService
) : CurrencyRepository {
    override fun getCurrencies(date: String): Single<List<Currency>> {
        return currencyRetrofitService.getCurrencies(date).map { dailyExRate ->
            dailyExRate.currencies.map { it.toCurrency() }
        }
    }
}