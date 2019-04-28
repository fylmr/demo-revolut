package com.fylmr.demo.revolut.ui.fragments.currencies.adapter

import androidx.recyclerview.widget.DiffUtil
import com.fylmr.demo.revolut.data.entities.Currency

class CurrenciesDiffUtil(
        private val oldList: List<Currency>,
        private val newList: List<Currency>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }
}