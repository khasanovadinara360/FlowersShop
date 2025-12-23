package com.example.flowersshop.ui.bouquet_card

interface BouquetCardEvents {
    data object OnAddClick: BouquetCardEvents
    data object OnDelClick: BouquetCardEvents
    data class OnFavouriteClick(val id: String): BouquetCardEvents
    data object OnBackClick: BouquetCardEvents
    data object OnCartClick: BouquetCardEvents
}