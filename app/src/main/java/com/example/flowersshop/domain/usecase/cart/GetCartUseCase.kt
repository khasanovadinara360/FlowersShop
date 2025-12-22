package com.example.flowersshop.domain.usecase.cart

import com.example.flowersshop.domain.model.CartModel
import com.example.flowersshop.domain.repository.CartRepository

class GetCartUseCase(
    private val repo: CartRepository
) {
    suspend fun execute(): Result<List<CartModel>> {
        return repo.getCart()
    }
}