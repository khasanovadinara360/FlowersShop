package com.example.flowersshop.ui.pages.bouquet

import com.example.flowersshop.domain.model.ItemModel

data class BouquetState(
    val flower: String = "",
    val green: String = "",
    val pack: String = "",
    val card: String = "",
    val page: Int = 0,
    val coast: Int = 0,
    val flowers: List<ItemModel> = emptyList(),
    val greens: List<ItemModel> = emptyList(),
    val packs: List<ItemModel> = emptyList(),
    val cards: List<ItemModel> = emptyList(),
    val items: List<ItemModel> = emptyList(),
    val isLoading: Boolean = true
)
