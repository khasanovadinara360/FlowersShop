package com.example.flowersshop.ui.pages.bouquet

import com.example.flowersshop.domain.model.ItemModel

interface BouquetEvents {
    data class OnFlowerClick(val value: ItemModel) : BouquetEvents
    data class OnGreenClick(val value: ItemModel) : BouquetEvents
    data class OnPackClick(val value: ItemModel) : BouquetEvents
    data class OnCardClick(val value: ItemModel) : BouquetEvents
    data object OnNextPageClick : BouquetEvents
}