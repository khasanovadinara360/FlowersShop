package com.example.flowersshop.domain.repository

import com.example.flowersshop.domain.model.BouquetModel

interface BouquetsRepository {
    suspend fun getBouquetsByCategory(category: String): Result<List<BouquetModel>>
    suspend fun getCategories(): Result<List<String>>
    suspend fun getBouquetBuild(flowerId: String, greenId: String): Result<String>
    suspend fun getBouquetById(flowerId: String): Result<BouquetModel>
    suspend fun updateBouquet(flowerId: String): Result<Unit>
}