package com.example.flowersshop.ui.bouquet

import com.example.flowersshop.domain.model.BouquetModel

data class BouquetCardState(
    val count: Int = 1,
    val bouquet: BouquetModel? = null,
    val isLoading: Boolean = true,
    val isFavourite: Boolean = false

)
