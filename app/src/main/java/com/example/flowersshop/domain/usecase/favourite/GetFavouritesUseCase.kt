package com.example.flowersshop.domain.usecase.favourite

import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.domain.repository.FavouriteRepository

class GetFavouritesUseCase(
    private val repo: FavouriteRepository
) {
    suspend fun execute(): Result<List<BouquetModel>> {
        return repo.getFavourites()
    }
}