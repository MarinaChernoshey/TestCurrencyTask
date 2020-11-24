package com.example.testtask.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.testtask.DatePickerChangeListener
import com.example.testtask.R
import com.example.testtask.TestTaskApplication
import com.example.testtask.databinding.FragmentCurrencyListBinding
import com.example.testtask.view_model.CurrencyListViewModel
import com.example.testtask.view_model.FilterViewModel
import java.util.*


class CurrencyListFragment : Fragment(), DatePickerChangeListener {

    private var viewModel: CurrencyListViewModel? = null
    private val filterViewModel by navGraphViewModels<FilterViewModel>(R.id.nav_graph)
    lateinit var binding: FragmentCurrencyListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_currency_list, container, false
        )

        binding.viewModel = viewModel
        binding.datePickerChangeListener = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel()
        viewModel?.loadCurrencies(Date()) {
            filterViewModel.items.addAll(it)
        }

        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        viewModel?.applyFilters(filterViewModel.items)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.filter_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.action_currencyListFragment_to_filterFragment)
        return super.onOptionsItemSelected(item)
    }


    override fun onChangeDate(date: Date) {
        viewModel?.loadCurrencies(date) {
            if (filterViewModel.items.isEmpty()) {
                filterViewModel.items.addAll(it)
            }
            viewModel?.applyFilters(filterViewModel.items)
        }
    }

    private fun provideViewModel(): CurrencyListViewModel {
        val getCurrenciesUseCase =
            (requireActivity().application as TestTaskApplication).dependencyFactory.provideGetCurrenciesUseCase()

        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CurrencyListViewModel(getCurrenciesUseCase) as T
            }
        }).get(CurrencyListViewModel::class.java)
    }

}
