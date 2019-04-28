package com.fylmr.demo.revolut.api

import com.fylmr.demo.revolut.api.entities.LatestCurrenciesPrices
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesService {

    @GET("latest")
    fun getLatest(@Query("base") base: String): Single<LatestCurrenciesPrices>

}