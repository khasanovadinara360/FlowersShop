package com.example.flowersshop.domain.usecase.cart

import com.example.flowersshop.domain.repository.CartRepository

class AddToCartUseCase(
    private val repo: CartRepository
) {
    suspend fun execute(productId: String, volume: Int): Result<Unit> {
        return repo.addToCart(productId, volume)
    }
}