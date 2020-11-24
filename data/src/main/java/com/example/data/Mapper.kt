package com.example.data

import com.example.data.model.Currency

fun Currency.toCurrency() = com.example.domain.model.Currency(
    id = currencyId,
    charCode = charCode,
    name = name,
    rate = rate
)