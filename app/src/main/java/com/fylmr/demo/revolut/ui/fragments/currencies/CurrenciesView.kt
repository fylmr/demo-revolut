package com.fylmr.demo.revolut.ui.fragments.currencies

import androidx.recyclerview.widget.DiffUtil
import com.arellomobile.mvp.MvpView

interface CurrenciesView : MvpView {

    fun showCurrenciesDifference(diffResult: DiffUtil.DiffResult)

}