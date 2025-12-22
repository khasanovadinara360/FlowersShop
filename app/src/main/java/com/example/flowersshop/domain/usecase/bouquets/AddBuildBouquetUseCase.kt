package com.example.flowersshop.domain.usecase.bouquets

import com.example.flowersshop.domain.model.CustomBouquetModel
import com.example.flowersshop.domain.repository.BouquetsRepository

class AddBuildBouquetUseCase(
    private val repo: BouquetsRepository
) {
    suspend fun execute(flowerId: String, greenId: String, packId: String, cardId: String, coast: Long): Result<CustomBouquetModel> {
        return repo.addBuildBouquet(flowerId, greenId, packId, cardId, coast)
    }
}