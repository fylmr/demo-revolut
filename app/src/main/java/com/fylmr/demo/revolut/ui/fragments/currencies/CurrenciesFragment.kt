package com.fylmr.demo.revolut.ui.fragments.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.fylmr.demo.revolut.R
import com.yscheduler.ui.base.mvp.MvpAppCompatFragment

class CurrenciesFragment: MvpAppCompatFragment() {

    @InjectPresenter
    lateinit var presenter: CurrenciesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currencies)
    }

}