package com.fylmr.demo.revolut.utils

import android.text.Editable
import android.text.TextWatcher

abstract class SimpleTextWatcher : TextWatcher {

    abstract override fun afterTextChanged(s: Editable?)

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}

fun onEditTextChanged(callback: (String) -> Unit): SimpleTextWatcher {
    return object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            callback(s.toString())
        }
    }
}