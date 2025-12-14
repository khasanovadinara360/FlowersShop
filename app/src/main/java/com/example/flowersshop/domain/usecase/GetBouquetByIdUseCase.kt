package com.example.flowersshop.domain.usecase

import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.domain.repository.BouquetsRepository

class GetBouquetByIdUseCase(
    private val repo: BouquetsRepository
) {
    suspend fun execute(id: String): Result<BouquetModel> {
        return  repo.getBouquetById(id)
    }
}