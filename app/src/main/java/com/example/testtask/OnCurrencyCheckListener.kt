package com.example.testtask

import com.example.testtask.model.CheckableItem

interface OnCurrencyCheckListener {
    fun onChecked(checkableItem: CheckableItem)
}