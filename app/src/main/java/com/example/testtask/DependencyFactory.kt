package com.example.testtask

import com.example.data.repository.CurrencyRepositoryImpl
import com.example.data.CurrencyRetrofitService
import com.example.domain.CurrencyRepository
import com.example.domain.use_case.GetCurrenciesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class DependencyFactory {

    @Volatile
    var retrofit: Retrofit? = null

    fun provideGetCurrenciesUseCase(): GetCurrenciesUseCase = GetCurrenciesUseCase(
        provideCurrenciesRepository(),
        Schedulers.io(),
        AndroidSchedulers.mainThread()
    )

    fun provideCurrenciesRepository(): CurrencyRepository =
        CurrencyRepositoryImpl(provideCurrencyRetrofitService())

    fun provideCurrencyRetrofitService(): CurrencyRetrofitService {
        val retrofit = provideRetrofit()
        return retrofit.create(CurrencyRetrofitService::class.java)
    }

    fun provideRetrofit(): Retrofit {
        if (retrofit == null) {
            synchronized(this) {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl("https://www.nbrb.by/Services/")
                        .addConverterFactory(SimpleXmlConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                }
            }
        }
        return retrofit!!
    }
}