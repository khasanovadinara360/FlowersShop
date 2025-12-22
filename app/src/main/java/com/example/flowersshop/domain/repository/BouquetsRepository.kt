package com.example.flowersshop.domain.repository

import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.domain.model.CustomBouquetModel

interface BouquetsRepository {
    suspend fun getBouquetsByCategory(category: String): Result<List<BouquetModel>>
    suspend fun getCategories(): Result<List<String>>
    suspend fun getBouquetBuild(flowerId: String, greenId: String): Result<String>
    suspend fun getBouquetById(flowerId: String): Result<BouquetModel>
    suspend fun updateBouquet(flowerId: String): Result<Unit>
    suspend fun addBuildBouquet(
        flowerId: String,
        greenId: String,
        packId: String,
        cardId: String,
        coast: Long
    ): Result<CustomBouquetModel>
    suspend fun getBuildBouquetById(id: String): Result<CustomBouquetModel>

}