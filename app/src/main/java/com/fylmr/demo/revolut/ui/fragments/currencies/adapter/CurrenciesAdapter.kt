package com.fylmr.demo.revolut.ui.fragments.currencies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fylmr.demo.revolut.R
import com.fylmr.demo.revolut.data.entities.Currency

class CurrenciesAdapter(
        private val currencies: List<Currency>
) : RecyclerView.Adapter<CurrenciesAdapter.ViewHolder>() {

    override fun getItemCount(): Int = currencies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currency = currencies[position]

        holder.title.text = currency.code
        holder.value.text = currency.price.toString()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.tv_currency_title)
        val value: TextView = v.findViewById(R.id.tv_currency_value)
    }

}