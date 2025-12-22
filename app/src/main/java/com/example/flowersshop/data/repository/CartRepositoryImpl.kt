package com.example.flowersshop.data.repository

import com.example.flowersshop.data.InitSupabaseClient.client
import com.example.flowersshop.data.dto.CartModelDto
import com.example.flowersshop.data.dto.toDomain
import com.example.flowersshop.domain.model.CartModel
import com.example.flowersshop.domain.repository.CartRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest

class CartRepositoryImpl : CartRepository {
    override suspend fun getCart(): Result<List<CartModel>> {
        val userId = client.auth.currentUserOrNull()?.id ?: return Result.failure(
            NullPointerException("Пользователь не авторизован")
        )
        return try {
            val res = client.postgrest["cart"].select {
                filter {
                    eq("user_id", userId)
                }
            }.decodeList<CartModelDto>().map { it.toDomain() }
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addToCart(productId: String, volume: Int): Result<Unit> {
        val userId = client.auth.currentUserOrNull()?.id ?: return Result.failure(
            NullPointerException("Пользователь не авторизован")
        )
        try {
            val res = client.postgrest["cart"].select {
                filter {
                    eq("user_id", userId)
                    eq("product_id", productId)
                }
            }.decodeList<CartModelDto>()
            if (res.isNotEmpty()) {
                val item = res.first()
                client.postgrest["cart"].update(
                    mapOf("volume" to item.volume + 1)
                )
                {
                    filter {
                        eq("id", item.id!!)
                    }
                }

            } else {
                client.postgrest["cart"].insert(
                    CartModelDto(
                        userId = userId,
                        productId = productId,
                        volume = volume
                    )
                )
            }
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun updateCart(items: List<CartModel>): Result<Unit> {
        val userId = client.auth.currentUserOrNull()?.id ?: return Result.failure(
            NullPointerException("Пользователь не авторизован")
        )
        return try {
            clearCart()

            items.forEach { item ->
                client.postgrest["cart"].insert(
                    CartModelDto(
                        userId = userId,
                        productId = item.productId,
                        customProductId = item.customProductId,
                        volume = item.volume
                    )
                )
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun clearCart(): Result<Unit> {
        val userId = client.auth.currentUserOrNull()?.id ?: return Result.failure(
            NullPointerException("Пользователь не авторизован")
        )
        return try {
            client.postgrest["cart"].delete {
                filter {
                    eq("user_id", userId)
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }


    }

    override suspend fun addToCartBuild(customProductId: String): Result<Unit> {
        val userId = client.auth.currentUserOrNull()?.id ?: return Result.failure(
            NullPointerException("Пользователь не авторизован")
        )
        try {

            client.postgrest["cart"].insert(
                CartModelDto(
                    userId = userId,
                    customProductId = customProductId,
                    volume = 1
                )
            )
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

//    override suspend fun delFavourite(productId: String): Result<Unit> {
//        val userId = client.auth.currentUserOrNull()?.id
//        return try {
//            if (userId != null) {
//                val res = client.postgrest["favourites"].delete {
//                    filter {
//                        eq("user_id", userId)
//                        eq("product_id", productId)
//                    }
//                }.decodeList<FavouriteModelDto>()
//
//                Result.success(Unit)
//            } else {
//                Result.failure(NullPointerException("Пользователь не авторизован"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
}