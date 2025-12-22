package com.example.flowersshop.ui.pages.fav

import com.example.flowersshop.domain.model.BouquetModel

data class FavouriteState(
    val products: List<BouquetModel> = emptyList(),
    val isLoading: Boolean = true
)
