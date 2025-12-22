package com.example.flowersshop.domain.usecase.bouquets

import com.example.flowersshop.domain.model.CustomBouquetModel
import com.example.flowersshop.domain.repository.BouquetsRepository

class GetBuildBouquetUseCase(
    private val repo: BouquetsRepository
) {
    suspend fun execute(id: String): Result<CustomBouquetModel> {
        return repo.getBuildBouquetById(id)
    }
}