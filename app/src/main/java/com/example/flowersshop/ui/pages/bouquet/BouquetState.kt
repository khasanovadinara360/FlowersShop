package com.example.flowersshop.ui.pages.bouquet

import com.example.flowersshop.domain.model.ItemModel

data class BouquetState(
    val flower: ItemModel? = null,
    val green: ItemModel? = null,
    val pack: ItemModel? = null,
    val card: ItemModel? = null,

    val isGreen: Boolean = false,
    val page: Int = 0,
    val coast: Long = 0,
    val resBouquetUrl: String = "",

    val flowers: List<ItemModel> = emptyList(),
    val flowersCount: Int = 1,
    val greens: List<ItemModel> = emptyList(),
    val greensCount: Int = 1,
    val packs: List<ItemModel> = emptyList(),
    val packsCount: Int = 1,
    val cards: List<ItemModel> = emptyList(),
    val cardsCount: Int = 1,
    val items: List<ItemModel> = emptyList(),
    val isLoading: Boolean = true
)
