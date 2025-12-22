package com.example.flowersshop.domain.repository

import com.example.flowersshop.domain.model.CartModel

interface CartRepository {
    suspend fun getCart(): Result<List<CartModel>>
    suspend fun addToCart(productId: String, volume: Int): Result<Unit>
    suspend fun addToCartBuild(customProductId: String): Result<Unit>
    suspend fun updateCart(items: List<CartModel>): Result<Unit>
    suspend fun clearCart(): Result<Unit>
}