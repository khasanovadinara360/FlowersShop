package com.example.flowersshop.domain.usecase.cart

import com.example.flowersshop.domain.repository.CartRepository

class AddBuildToCartUseCase(
    private val repo: CartRepository
) {
    suspend fun execute(customProductId: String): Result<Unit> {
        return repo.addToCartBuild(customProductId)
    }
}