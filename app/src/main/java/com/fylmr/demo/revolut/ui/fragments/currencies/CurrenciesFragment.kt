package com.fylmr.demo.revolut.ui.fragments.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.fylmr.demo.revolut.R
import com.fylmr.demo.revolut.data.entities.Currency
import com.fylmr.demo.revolut.ui.fragments.currencies.adapter.CurrenciesAdapter
import com.yscheduler.ui.base.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.fragment_currencies.*

class CurrenciesFragment : MvpAppCompatFragment(), CurrenciesView {

    @InjectPresenter
    lateinit var presenter: CurrenciesPresenter

    private lateinit var rvCurrencies: RecyclerView
    private lateinit var adapter: CurrenciesAdapter
    private val currencies = mutableListOf<Currency>()

    // ===================================================
    // Creation
    // ===================================================

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currencies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = CurrenciesAdapter(currencies)
        rvCurrencies = rv_currencies
        rvCurrencies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvCurrencies.adapter = adapter
    }

    // ===================================================
    // CurrenciesView
    // ===================================================

}