package com.example.flowersshop.data.repository

import com.example.flowersshop.data.InitSupabaseClient.client
import com.example.flowersshop.data.dto.BouquetModelDto
import com.example.flowersshop.data.dto.toDomain
import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.domain.repository.BouquetsRepository
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class BouquetsRepositoryImpl() : BouquetsRepository {
    override suspend fun getBouquetsByCategory(category: String): Result<List<BouquetModel>> {
        return try {
            val list = client.postgrest["bouquets"].select {
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

    override suspend fun getCategories(): Result<List<String>> {
        return try {
            val list =
                client.postgrest["bouquets"].select(Columns.list("category"))
                    .decodeList<Map<String, String>>().map { it["category"]!! }.distinct()

            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBouquetById(flowerId: String): Result<BouquetModel> {
        return try {
            val bouquet = client.postgrest["bouquets"].select {
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
            val url = client.postgrest["bouquetBuild"].select(Columns.list("imageUrl")) {
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