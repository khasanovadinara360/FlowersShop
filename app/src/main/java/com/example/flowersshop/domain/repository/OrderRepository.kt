package com.example.flowersshop.domain.repository

import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.domain.model.CustomBouquetModel

interface OrderRepository {
    suspend fun addOrder(
        totalCoast: Long,
        address: String,
        payment: String,
        delivery: String,
        phone: String,
        comment: String,
        products: List<String>,
        customBouquets: List<String>,
    ): Result<Unit>
}