package com.example.flowersshop.domain.usecase.cart

import com.example.flowersshop.domain.model.CartModel
import com.example.flowersshop.domain.repository.CartRepository


class UpdateCartUseCase(
    private val repo: CartRepository
) {
    suspend fun execute(items: List<CartModel>): Result<Unit> {
        return repo.updateCart(items)
    }
}