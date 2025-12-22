package com.example.flowersshop.data.repository

import com.example.flowersshop.data.InitSupabaseClient.client
import com.example.flowersshop.data.dto.BouquetModelDto
import com.example.flowersshop.data.dto.FavouriteModelDto
import com.example.flowersshop.data.dto.toDomain
import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.domain.repository.FavouriteRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest

class FavouriteRepositoryImpl : FavouriteRepository {
    override suspend fun addFavourite(productId: String): Result<Unit> {
        val userId = client.auth.currentUserOrNull()?.id
        try {
            val exist = client.postgrest["favourites"].select {
                filter {
                    eq("user_id", userId!!)
                    eq("product_id", productId)
                }
            }.decodeList<FavouriteModelDto>().size
            if (exist < 1) {
                client.postgrest["favourites"].insert(
                    if (userId != null) {
                        FavouriteModelDto(
                            productId = productId,
                            userId = userId
                        )
                    } else {
                        return Result.failure(NullPointerException("Пользователь не авторизован"))
                    }
                )
            }
            return Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun delFavourite(productId: String): Result<Unit> {
        val userId = client.auth.currentUserOrNull()?.id
        return try {
            if (userId != null) {
                val res = client.postgrest["favourites"].delete {
                    filter {
                        eq("user_id", userId)
                        eq("product_id", productId)
                    }
                }.decodeList<FavouriteModelDto>()

                Result.success(Unit)
            } else {
                Result.failure(NullPointerException("Пользователь не авторизован"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isFavourite(productId: String): Result<Boolean> {
        val userId = client.auth.currentUserOrNull()?.id ?: return Result.failure(
            NullPointerException("Пользователь не авторизован")
        )

        return try {
            val res = client.postgrest["favourites"]
                .select {
                    filter {
                        eq("user_id", userId)
                        eq("product_id", productId)
                    }
                    limit(count = 1)
                }
                .decodeList<FavouriteModelDto>()

            Result.success(res.isNotEmpty())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFavourites(): Result<List<BouquetModel>> {
        val userId = client.auth.currentUserOrNull()?.id ?: return Result.failure(
            NullPointerException("Пользователь не авторизован")
        )
        return try {
            val fav = client.postgrest["favourites"].select {
                filter {
                    eq("user_id", userId)
                }
            }.decodeList<FavouriteModelDto>()
            val res = fav.map {i ->
                client.postgrest["products"].select {
                    filter {
                        eq("id", i.productId)
                    }
                }.decodeList<BouquetModelDto>().map { it.toDomain() }[0]
            }
//            val res1 = fav.forEach {
//
//            }
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}