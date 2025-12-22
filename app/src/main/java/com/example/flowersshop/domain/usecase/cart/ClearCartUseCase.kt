package com.example.flowersshop.domain.usecase.cart

import com.example.flowersshop.domain.repository.CartRepository

class ClearCartUseCase(
    private val repo: CartRepository
) {
    suspend fun execute() : Result<Unit> {
        return repo.clearCart()
    }

}