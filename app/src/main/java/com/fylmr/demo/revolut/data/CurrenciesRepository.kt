package com.fylmr.demo.revolut.data

import com.fylmr.demo.revolut.api.CurrenciesService
import com.fylmr.demo.revolut.api.entities.LatestCurrenciesPrices
import com.fylmr.demo.revolut.data.entities.Currency
import io.reactivex.Observable
import org.koin.core.KoinComponent
import java.util.concurrent.TimeUnit

interface CurrenciesRepository {

    fun getRelativeToEuro(updateSeconds: Long = 1): Observable<List<Currency>>

}

class CurrenciesRepositoryImpl(
        private val service: CurrenciesService
) : CurrenciesRepository, KoinComponent {

    companion object {
        private const val euroCode = "EUR"
    }

    override fun getRelativeToEuro(updateSeconds: Long): Observable<List<Currency>> {
        return Observable.interval(updateSeconds, TimeUnit.SECONDS)
                .flatMapSingle { service.getLatest(euroCode) }
                .map(::mapLatestCurrencies)
    }

    private fun mapLatestCurrencies(response: LatestCurrenciesPrices): List<Currency> {
        return response.rates::class.java.declaredFields.map {
            it.isAccessible = true

            val price = it.get(response.rates) as Double

            Currency(it.name, price)
        }
    }
}