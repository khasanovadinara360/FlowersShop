package com.example.flowersshop.domain.usecase.bouquets

import com.example.flowersshop.domain.repository.BouquetsRepository

class GetBouquetBuildUseCase(
    private val repo: BouquetsRepository
) {
    suspend fun execute(flowerId: String, greenId: String): Result<String> {
        return repo.getBouquetBuild(flowerId, greenId)
    }
}