package com.example.testtask.view_model

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.Currency
import com.example.domain.use_case.GetCurrenciesUseCase
import com.example.testtask.BR
import com.example.testtask.R
import com.example.testtask.model.CheckableItem
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import java.util.*

class CurrencyListViewModel(private val getCurrenciesUseCase: GetCurrenciesUseCase) : ViewModel() {

    private lateinit var items: ArrayList<Currency>
    val error: ObservableField<String> = ObservableField()
    val startTime = ObservableField(Date())
    val expireTime = ObservableField(Date())
    val minTime = ObservableField(Date(0))
    val itemBinding = ItemBinding.of<Currency>(BR.item, R.layout.item_currency)
    val filteredItems: DiffObservableList<Currency> =
            DiffObservableList(object : DiffUtil.ItemCallback<Currency>() {
                override fun areContentsTheSame(oldItem: Currency, newItem: Currency) =
                        oldItem == newItem

                override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                        oldItem.id == newItem.id
            })

    fun loadCurrencies(date: Date, onResult: (List<CheckableItem>) -> Unit) {
        getCurrenciesUseCase.getCurrencies(date)
                .subscribe({ currencies ->
                    items.clear()
                    items.addAll(currencies)
                    filteredItems.update(currencies)
                    onResult(currencies.map { CheckableItem(it.id, it.name) })
                    error.set("")
                }, { throwable ->
                    error.set(throwable.message)
                })
    }

    fun applyFilters(itemsFiltered: List<CheckableItem>) {
        val idsNotChecked = itemsFiltered.filter { !it.isCheck }.map { it.id }
        filteredItems.update(items.filter { it.id !in idsNotChecked })

    }

}