package com.example.testtask.view_model

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.Currency
import com.example.domain.use_case.GetCurrenciesUseCase
import com.example.testtask.BR
import com.example.testtask.DatePickerChangeListener
import com.example.testtask.R
import com.example.testtask.model.CheckableItem
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class CurrencyListViewModel(private val getCurrenciesUseCase: GetCurrenciesUseCase) : ViewModel() {

    private val items: ArrayList<Currency> = arrayListOf()
    val filteredItems: DiffObservableList<Currency> =
        DiffObservableList(object : DiffUtil.ItemCallback<Currency>() {

            override fun areContentsTheSame(oldItem: Currency, newItem: Currency) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                oldItem.id == newItem.id
        })
    val error: ObservableField<String> = ObservableField("")

    var testDateString = "02/04/2014"
    var df: DateFormat = SimpleDateFormat("dd/MM/yyyy")

    var d1: Date = df.parse(testDateString)
    val startTime = ObservableField(Date())
    val expireTime = ObservableField(Date())
    val minTime = ObservableField(df.parse(testDateString))

    val itemBinding = ItemBinding.of<Currency>(BR.item, R.layout.item_currency)


    fun loadCurrencies(date: Date, onResult: (List<CheckableItem>) -> Unit) {
        getCurrenciesUseCase.getCurrencies(date)
            .subscribe({ currencies ->
                items.clear()
                items.addAll(currencies)
                filteredItems.update(currencies)
                error.set("")
                onResult(currencies.map { CheckableItem(it.id, it.name) })
            }, { error ->
                this.error.set(error.message)
            })
    }

    fun applyFilters(itemsFiltered: List<CheckableItem>) {
        val idsNotChecked = itemsFiltered.filter { !it.isCheck }.map { it.id }
        filteredItems.update(items.filter { it.id !in idsNotChecked })

    }

}