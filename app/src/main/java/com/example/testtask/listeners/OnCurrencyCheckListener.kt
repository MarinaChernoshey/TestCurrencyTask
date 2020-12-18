package com.example.testtask.listeners

import com.example.testtask.model.CheckableItem

interface OnCurrencyCheckListener {
    fun onChecked(checkableItem: CheckableItem)
}