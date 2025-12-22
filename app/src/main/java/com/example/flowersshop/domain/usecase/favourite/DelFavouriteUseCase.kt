package com.example.flowersshop.domain.usecase.favourite

import com.example.flowersshop.domain.repository.FavouriteRepository

class DelFavouriteUseCase(
    private val repo: FavouriteRepository
) {
    suspend fun execute(productId: String): Result<Unit> {
        return repo.delFavourite(productId)

    }
}