package com.example.flowersshop.ui.main

import com.example.flowersshop.domain.model.BouquetModel

data class MainState(
    val categories: List<String> = emptyList(),
    val bouquets: List<BouquetModel> = emptyList(),
    val category: String = "",
    val isSuccess: Boolean = false
)
