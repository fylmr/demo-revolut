package com.fylmr.demo.revolut.api

import com.fylmr.demo.revolut.data.entities.Currency
import io.reactivex.Single
import retrofit2.http.GET

interface CurrenciesService {

    @GET("latest?base={base}")
    fun getLatest(base: String): Single<List<Currency>>

}