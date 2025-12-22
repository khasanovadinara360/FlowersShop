package com.example.flowersshop.domain.usecase.order

import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.domain.model.CustomBouquetModel
import com.example.flowersshop.domain.repository.OrderRepository

class AddOrderUseCase(
    private val repo: OrderRepository
) {
    suspend fun execute(
        totalCoast: Long,
        address: String,
        payment: String,
        delivery: String,
        phone: String,
        comment: String,
        products: List<String>,
        customBouquets: List<String>,
    ): Result<Unit> {
        return repo.addOrder(totalCoast, address, payment, delivery, phone, comment, products, customBouquets)
    }
}