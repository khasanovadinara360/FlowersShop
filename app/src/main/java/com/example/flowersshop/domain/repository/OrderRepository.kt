package com.example.flowersshop.domain.repository

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