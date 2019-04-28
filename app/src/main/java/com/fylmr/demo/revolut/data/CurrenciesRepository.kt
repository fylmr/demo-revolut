package com.fylmr.demo.revolut.data

import com.fylmr.demo.revolut.api.CurrenciesService
import com.fylmr.demo.revolut.api.entities.LatestCurrenciesPrices
import com.fylmr.demo.revolut.data.entities.Currency
import io.reactivex.Single
import org.koin.core.KoinComponent

interface CurrenciesRepository {

    fun getRelativeToEuro(): Single<List<Currency>>

}

class CurrenciesRepositoryImpl(
        private val service: CurrenciesService
) : CurrenciesRepository, KoinComponent {

    companion object {
        private const val euroCode = "EUR"
    }

    override fun getRelativeToEuro(): Single<List<Currency>> {
        return service.getLatest(euroCode)
                .map(::mapLatestCurrencies)
    }

    private fun mapLatestCurrencies(response: LatestCurrenciesPrices): List<Currency> {
        TODO()
    }
}