package com.example.flowersshop.ui.cart

import com.example.flowersshop.domain.model.ItemModel

data class CartState(
    val totalCoast: Long = 0,
    val products: List<CartItem> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = true,
    val isSuccess: Boolean = false,
    val errorMessage: String = ""


)
sealed class CartItem {
    data class CartBouquet(
        val id: String,
        val title: String,
        val coast: Long,

        val volume: Int
    ) : CartItem()

    data class CustomBouquet(
        val id: String,
        val title: String,
        val coast: Long,
        val components: List<ItemModel>,
        val volume: Int
    ) : CartItem()
}
