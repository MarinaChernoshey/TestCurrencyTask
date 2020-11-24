package com.example.testtask.view_model

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import com.example.testtask.BR
import com.example.testtask.OnCurrencyCheckListener
import com.example.testtask.R
import com.example.testtask.model.CheckableItem
import me.tatarka.bindingcollectionadapter2.ItemBinding

class FilterViewModel : ViewModel(), OnCurrencyCheckListener {
    val items: ObservableList<CheckableItem> = ObservableArrayList()
    val itemBinding = ItemBinding.of<CheckableItem> { itemBinding, _, _ ->
        itemBinding.set(BR.item, R.layout.item_currency_filter)
            .bindExtra(BR.onCheckedListener, this)
    }

    val error: ObservableField<String>? = null

    override fun onChecked(checkableItem: CheckableItem) {
        checkableItem.isCheck = !checkableItem.isCheck
    }

    fun update(checkableItem: CheckableItem) {
        checkableItem.isCheck = !checkableItem.isCheck
    }

}