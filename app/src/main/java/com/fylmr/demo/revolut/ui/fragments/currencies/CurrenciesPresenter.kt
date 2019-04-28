package com.fylmr.demo.revolut.ui.fragments.currencies

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fylmr.demo.revolut.data.CurrenciesRepository
import com.fylmr.demo.revolut.data.entities.Currency
import com.fylmr.demo.revolut.ui.fragments.currencies.adapter.CurrenciesAdapterPresenter
import com.fylmr.demo.revolut.ui.fragments.currencies.adapter.CurrenciesDiffUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

@InjectViewState
class CurrenciesPresenter : MvpPresenter<CurrenciesView>(), CurrenciesAdapterPresenter, KoinComponent {

    companion object {
        private const val TAG = "CurrenciesPresenter"
    }

    private val repo: CurrenciesRepository by inject()

    private val currencies = mutableListOf<Currency>()

    private val compositeDisposable = CompositeDisposable()

    // ===================================================
    // Lifecycle
    // ===================================================

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        startCurrencyListener()
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.dispose()
    }

    // ===================================================
    // CurrenciesAdapterPresenter
    // ===================================================

    override fun getCurrencies(): List<Currency> {
        return currencies
    }

    override fun onEdited(position: Int, newValue: String) {
        Log.d(TAG, "onEdited() pos: $position, newValue: $newValue")

        // todo updates without user actions
    }

    override fun onClicked(position: Int) {
        if (currencies[position].isActive == 1)
            return

        Log.d(TAG, "onClicked() pos: $position")

        clearActiveStatus()

        val newCurrencies = currencies.toMutableList()
        newCurrencies[position].isActive = 1

        showCurrencies(newCurrencies.sorted())

        viewState.scrollToTop()
    }

    // ===================================================
    // Private
    // ===================================================

    private fun startCurrencyListener() {
        val d = repo.getRelativeToEuro()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { Log.v(TAG, "Currencies updated") }
                .doOnError { Log.e(TAG, "getRelativeToEuro error", it) }
                .map { list ->
                    list.map m@{ c ->
                        val currentStatus = currencies.find { it.code == c.code }?.isActive
                                ?: return@m c
                        Currency(c.code, c.price, currentStatus)
                    }
                }
                .map { it.sorted() }
                .subscribe(::showCurrencies)

        compositeDisposable.add(d)
    }

    private fun showCurrencies(newCurrencies: List<Currency>) {
        val diffResult = DiffUtil.calculateDiff(CurrenciesDiffUtil(currencies, newCurrencies))

        currencies.clear()
        currencies.addAll(newCurrencies)

        viewState.showCurrenciesDifference(diffResult)
    }

    private fun clearActiveStatus() {
        currencies
                .filter { it.isActive != 0 }
                .forEach { it.isActive = 0 }
    }

    private fun List<Currency>.sorted(): List<Currency> {
        return sortedWith(Comparator { c1, c2 -> c2.isActive - c1.isActive })
    }
}