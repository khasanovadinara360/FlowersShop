package com.example.flowersshop.data.repository

import com.example.flowersshop.data.InitSupabaseClient.client
import com.example.flowersshop.data.dto.BouquetModelDto
import com.example.flowersshop.data.dto.CustomBouquetModelDto
import com.example.flowersshop.data.dto.toDomain
import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.domain.model.CustomBouquetModel
import com.example.flowersshop.domain.repository.BouquetsRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class BouquetsRepositoryImpl() : BouquetsRepository {
    override suspend fun getBouquetsByCategory(category: String): Result<List<BouquetModel>> {
        return try {
            val list = client.postgrest["products"].select {
                filter {
                    eq("category", category)
                }
            }.decodeList<BouquetModelDto>().map {
                it.toDomain()
            }
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun addBuildBouquet(
        flowerId: String,
        greenId: String,
        packId: String,
        cardId: String,
        coast: Long
    ): Result<CustomBouquetModel> {
        val userId = client.auth.currentUserOrNull()?.id ?: return Result.failure(
            NullPointerException("Пользователь не авторизован")
        )
        return try {
            val size = client.postgrest["custom_bouquets"].select {
                filter {
                    eq("user_id", userId)
                }
            }.decodeList<CustomBouquetModelDto>().size
            val res = client.postgrest["custom_bouquets"].insert(
                CustomBouquetModelDto(
                    title = "Сборный букет №${size + 1}",
                    flowerId = flowerId,
                    greenId = greenId,
                    packId = packId,
                    cardId = cardId,
                    userId = userId,
                    coast = coast
                )
            ){
                select()
            }.decodeSingle<CustomBouquetModelDto>()
            Result.success(res.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun getBuildBouquetById(id: String): Result<CustomBouquetModel> {

        return try {
            val res = client.postgrest["custom_bouquets"].select {
                filter {
                    eq("id", id)
                }
            }.decodeSingle<CustomBouquetModelDto>().toDomain()
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateBouquet(flowerId: String): Result<Unit> {
        return try {
            val res = client.postgrest["products"].update {

            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCategories(): Result<List<String>> {
        return try {
            val list =
                client.postgrest["products"].select(Columns.list("category"))
                    .decodeList<Map<String, String>>().map { it["category"]!! }.distinct()

            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBouquetById(flowerId: String): Result<BouquetModel> {
        return try {
            val bouquet = client.postgrest["products"].select {
                filter {
                    eq("id", flowerId)
                }
            }.decodeList<BouquetModelDto>().map { it.toDomain() }
            Result.success(bouquet[0])
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBouquetBuild(flowerId: String, greenId: String): Result<String> {
        return try {
            val url = client.postgrest["bouquet_builder"].select(Columns.list("imageUrl")) {
                filter {
                    eq("flower_id", flowerId)
                    eq("green_id", greenId)
                }
            }
                .decodeList<Map<String, String>>().map { it["imageUrl"]!! }
            Result.success(url[0])
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}