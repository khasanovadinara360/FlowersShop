package com.example.flowersshop.data.repository

import com.example.flowersshop.data.InitSupabaseClient.client
import com.example.flowersshop.data.dto.OrderItemsModelDto
import com.example.flowersshop.data.dto.OrderModelDto
import com.example.flowersshop.domain.repository.OrderRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest

class OrderRepositoryImpl : OrderRepository {
    override suspend fun addOrder(
        totalCoast: Long,
        address: String,
        payment: String,
        delivery: String,
        phone: String,
        comment: String,
        products: List<String>,
        customBouquets: List<String>,
    ): Result<Unit> {
        val userId = client.auth.currentUserOrNull()?.id ?: return Result.failure(
            NullPointerException("Пользователь не авторизован")
        )
        return try {
            val res = client.postgrest["orders"].insert(
                OrderModelDto(
                    userId = userId,
                    payment = payment,
                    delivery = delivery,
                    phone = phone,
                    address = address,
                    totalPrice = totalCoast,
                    comment = comment
                )
            ) {
                select()
            }.decodeSingle<OrderModelDto>()
            products.forEach { i ->

                client.postgrest["order_items"].insert(
                    OrderItemsModelDto(
                        orderId = res.id!!,
                        productId = i
                    )
                )
            }
            customBouquets.forEach { i ->
                client.postgrest["order_items"].insert(
                    OrderItemsModelDto(
                        orderId = res.id!!,
                        customProductId = i
                    )
                )
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}