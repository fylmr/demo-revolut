package com.fylmr.demo.revolut.api.entities

import com.fylmr.demo.revolut.data.entities.Currency
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

data class LatestCurrenciesPrices(
        private val base: String,
        private val date: String,
        private val rates: Rates
)

data class Rates(
        private val AUD: Double,
        private val BGN: Double,
        private val BRL: Double,
        private val CAD: Double,
        private val CHF: Double,
        private val CNY: Double,
        private val CZK: Double,
        private val DKK: Double,
        private val GBP: Double,
        private val HKD: Double,
        private val HRK: Double,
        private val HUF: Double,
        private val IDR: Double,
        private val ILS: Double,
        private val INR: Double,
        private val ISK: Double,
        private val JPY: Double,
        private val KRW: Double,
        private val MXN: Double,
        private val MYR: Double,
        private val NOK: Double,
        private val NZD: Double,
        private val PHP: Double,
        private val PLN: Double,
        private val RON: Double,
        private val RUB: Double,
        private val SEK: Double,
        private val SGD: Double,
        private val THB: Double,
        private val TRY: Double,
        private val USD: Double,
        private val ZAR: Double
)

class LatestCurrenciesPricesDeserializer : JsonDeserializer<LatestCurrenciesPrices> {
    override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
    ): List<Currency> {

        val rates = json?.asJsonObject?.get("rates")?.asJsonObject
                ?: throw JsonParseException("Couldn't parse Currency from JSON: $json")
        return listOf(
                rates["AUD"],
                rates["BGN"],
                rates["BRL"],
                rates["CAD"],
                rates["CHF"],
                rates["CNY"],
                rates["CZK"],
                rates["DKK"],
                rates["GBP"],
                rates["HKD"],
                rates["HRK"],
                rates["HUF"]
,        TODO()

        )

    }
}