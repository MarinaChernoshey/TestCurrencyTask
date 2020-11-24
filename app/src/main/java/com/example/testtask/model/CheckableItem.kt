package com.example.testtask.model

import com.example.domain.model.Currency

data class CheckableItem(val id: String, val name: String, var isCheck: Boolean = true)