package com.example.testtask

import android.app.DatePickerDialog
import android.content.Context
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import java.util.*


@BindingAdapter(
    value = ["bind:datePick", "bind:maxDate", "bind:minDate", "datePickerChangeListener"],
    requireAll = false
)
fun bindTextViewDateClicks(
    textView: TextView,
    date: ObservableField<Date?>?,
    maxDate: ObservableField<Date?>?,
    minDate: ObservableField<Date?>?,
    datePickerChangeListener: DatePickerChangeListener
) {
    textView.setOnClickListener {
        selectDate(
            textView.context,
            date,
            maxDate,
            minDate,
            datePickerChangeListener
        )
    }
}

fun selectDate(
    context: Context?,
    date: ObservableField<Date?>?,
    maxDate: ObservableField<Date?>?,
    minDate: ObservableField<Date?>?,
    datePickerChangeListener: DatePickerChangeListener
) {
    val calBefore = Calendar.getInstance()
    if (date?.get() != null) calBefore.time = date.get()
    val dialog = context?.let {
        DatePickerDialog(
            it,
            { _, year, monthOfYear, dayOfMonth ->
                val cal = Calendar.getInstance()
                cal[Calendar.YEAR] = year
                cal[Calendar.MONTH] = monthOfYear
                cal[Calendar.DAY_OF_MONTH] = dayOfMonth
                date!!.set(cal.time)
                datePickerChangeListener.onChangeDate(date.get()!!)
                date.notifyChange()

            }, calBefore[Calendar.YEAR], calBefore[Calendar.MONTH], calBefore[Calendar.DAY_OF_MONTH]
        )
    }
    dialog?.let {
        if (minDate?.get() != null) dialog.datePicker.minDate = minDate.get()!!.time
        if (maxDate?.get() != null) dialog.datePicker.maxDate = maxDate.get()!!.time
        dialog.show()
    }
}