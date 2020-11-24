package com.example.data

import com.example.data.model.DailyExRate
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyRetrofitService {
    @GET("XmlExRates.aspx")
    fun getCurrencies(@Query("ondate") date: String) : Single<DailyExRate>
}
