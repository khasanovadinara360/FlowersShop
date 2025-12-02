package com.example.flowersshop.ui.pages.bouquet

interface BouquetEvents {
    data class OnFlowerClick(val value: String) : BouquetEvents
    data class OnGreenClick(val value: String) : BouquetEvents
    data class OnPackClick(val value: String) : BouquetEvents
    data class OnCardClick(val value: String) : BouquetEvents
}