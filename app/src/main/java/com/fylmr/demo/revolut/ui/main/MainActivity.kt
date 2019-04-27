package com.fylmr.demo.revolut.ui.main

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.fylmr.demo.revolut.R
import com.fylmr.demo.revolut.ui.fragments.currencies.CurrenciesFragment
import com.yscheduler.ui.base.mvp.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    // ===================================================
    // Lifecycle
    // ===================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // ===================================================
    // MainView
    // ===================================================

    override fun showCurrenciesFragment() {
        val fragment = CurrenciesFragment()

        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_holder, fragment)
                .commit()
    }
}
