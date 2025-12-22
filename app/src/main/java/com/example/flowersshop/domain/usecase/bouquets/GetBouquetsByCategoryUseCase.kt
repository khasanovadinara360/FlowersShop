package com.example.flowersshop.domain.usecase.bouquets

import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.domain.repository.BouquetsRepository

class GetBouquetsByCategoryUseCase(private val repo: BouquetsRepository) {
    suspend fun execute(category: String): Result<List<BouquetModel>> {
        return repo.getBouquetsByCategory(category)
    }
}