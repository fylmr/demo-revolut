package com.fylmr.demo.revolut.ui.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        startCurrenciesFragment()
    }

    private fun startCurrenciesFragment() {
        viewState.showCurrenciesFragment()
    }

}