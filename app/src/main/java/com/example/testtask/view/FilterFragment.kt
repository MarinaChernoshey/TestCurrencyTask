package com.example.testtask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.example.testtask.listeners.OnCurrencyCheckListener
import com.example.testtask.view_model.FilterViewModel
import com.example.testtask.R
import com.example.testtask.databinding.FragmentFilterBinding
import com.example.testtask.model.CheckableItem

class FilterFragment : Fragment(), OnCurrencyCheckListener {

    private val viewModel by navGraphViewModels<FilterViewModel>(R.id.nav_graph)
    lateinit var binding : FragmentFilterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onChecked(checkableItem: CheckableItem) {
        viewModel.onChecked(checkableItem)
    }

}