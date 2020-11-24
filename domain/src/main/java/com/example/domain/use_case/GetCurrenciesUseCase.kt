package com.example.domain.use_case

import com.example.domain.CurrencyRepository
import com.example.domain.model.Currency
import io.reactivex.Scheduler
import io.reactivex.Single
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class GetCurrenciesUseCase(
    private val currencyRepository: CurrencyRepository,
    private val workScheduler: Scheduler,
    private val resultScheduler: Scheduler
) {
    fun getCurrencies(date: Date): Single<List<Currency>> {
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy")

        return currencyRepository.getCurrencies(dateFormat.format(date))
            .subscribeOn(workScheduler)
            .observeOn(resultScheduler)
    }
}