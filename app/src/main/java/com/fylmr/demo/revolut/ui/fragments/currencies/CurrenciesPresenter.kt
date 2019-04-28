package com.fylmr.demo.revolut.ui.fragments.currencies

import androidx.recyclerview.widget.DiffUtil
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fylmr.demo.revolut.data.entities.Currency
import com.fylmr.demo.revolut.ui.fragments.currencies.adapter.CurrenciesDiffUtil

@InjectViewState
class CurrenciesPresenter : MvpPresenter<CurrenciesView>() {

    private val currencies = mutableListOf<Currency>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        startCurrencyListener()
    }

    fun getCurrencies(): List<Currency> {
        return currencies
    }

    private fun startCurrencyListener() {
        val currencies = listOf(
                Currency("ABC", 72.01),
                Currency("BCA", 74.1),
                Currency("CAB", 72.1999))

        showCurrencies(currencies)
    }

    private fun showCurrencies(newCurrencies: List<Currency>) {
        val diffResult = DiffUtil.calculateDiff(CurrenciesDiffUtil(currencies, newCurrencies))

        currencies.clear()
        currencies.addAll(newCurrencies)

        viewState.showCurrenciesDifference(diffResult)
    }
}