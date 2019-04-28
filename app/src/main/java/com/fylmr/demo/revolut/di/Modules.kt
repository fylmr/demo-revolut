package com.fylmr.demo.revolut.di

import com.fylmr.demo.revolut.api.CurrenciesService
import com.fylmr.demo.revolut.api.entities.LatestCurrenciesPrices
import com.fylmr.demo.revolut.api.entities.LatestCurrenciesPricesDeserializer
import com.fylmr.demo.revolut.data.CurrenciesRepository
import com.fylmr.demo.revolut.data.CurrenciesRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val apiModule = module {


    single<RxJava2CallAdapterFactory> {
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    single { LatestCurrenciesPricesDeserializer() }

    single<Gson> {
        GsonBuilder()
                .registerTypeAdapter(LatestCurrenciesPrices::class.java, get<LatestCurrenciesPricesDeserializer>())
                .create()
    }

    single<Retrofit> {
        Retrofit.Builder()
                .baseUrl("https://revolut.duckdns.org/")
                .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
    }

    single<CurrenciesService> {
        get<Retrofit>().create(CurrenciesService::class.java)
    }

    single<CurrenciesRepository> {
        CurrenciesRepositoryImpl(get())
    }

}