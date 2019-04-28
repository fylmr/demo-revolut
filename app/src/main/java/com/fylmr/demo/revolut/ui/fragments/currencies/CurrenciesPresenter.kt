package com.fylmr.demo.revolut.ui.fragments.currencies

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fylmr.demo.revolut.data.CurrenciesRepository
import com.fylmr.demo.revolut.data.entities.Currency
import com.fylmr.demo.revolut.ui.fragments.currencies.adapter.CurrenciesDiffUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

@InjectViewState
class CurrenciesPresenter : MvpPresenter<CurrenciesView>(), KoinComponent {

    private val repo: CurrenciesRepository by inject()

    private val currencies = mutableListOf<Currency>()

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        startCurrencyListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun getCurrencies(): List<Currency> {
        return currencies
    }

    private fun startCurrencyListener() {
        val d = repo.getRelativeToEuro()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::showCurrencies) {
                    Log.e("CurrenciesPresenter", "getRelativeToEuro error", it)
                }

        compositeDisposable.add(d)
    }

    private fun showCurrencies(newCurrencies: List<Currency>) {
        val diffResult = DiffUtil.calculateDiff(CurrenciesDiffUtil(currencies, newCurrencies))

        currencies.clear()
        currencies.addAll(newCurrencies)

        viewState.showCurrenciesDifference(diffResult)
    }
}