package com.example.flowersshop.domain.usecase.favourite

import com.example.flowersshop.domain.repository.FavouriteRepository

class IsBouquetFavouriteUseCase(
    private val repo: FavouriteRepository
) {
    suspend fun execute(productId: String): Result<Boolean> {
        return repo.isFavourite(productId)
    }
}