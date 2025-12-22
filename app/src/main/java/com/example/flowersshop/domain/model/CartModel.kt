package com.example.flowersshop.domain.model

data class CartModel(
    val id: String? = null,
    val userId: String,
    val productId: String? = null,
    val customProductId: String? = null,
    val volume: Int
)
