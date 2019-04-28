package com.fylmr.demo.revolut.data.entities

import androidx.annotation.IntRange

data class Currency(
        val code: String,
        val price: Double,
        @IntRange(from = 0, to = 1) val isActive: Int = 0
)