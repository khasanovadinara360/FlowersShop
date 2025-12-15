package com.example.flowersshop.data.repository

import com.example.flowersshop.data.InitSupabaseClient.client
import com.example.flowersshop.data.dto.ItemModelDto
import com.example.flowersshop.data.dto.toDomain
import com.example.flowersshop.domain.model.ItemModel
import com.example.flowersshop.domain.repository.ItemsRepository
import io.github.jan.supabase.postgrest.postgrest

class ItemsRepositoryImpl: ItemsRepository {
    override suspend fun getItems(): List<ItemModel> {
        val list = client.postgrest["items"].select().decodeList<ItemModelDto>()
        val listItems = mutableListOf< ItemModel>()
        list.forEach { i ->
            listItems.add(i.toDomain())
        }
        return listItems
    }

    override suspend fun getItemsByCategory(category: String): List<ItemModel> {
        return try {
            client.postgrest["items"].select {
                filter {
                    eq("category", category)
                }
            }.decodeList<ItemModelDto>()
                .map { it.toDomain() }


        } catch (e: Exception) {
            emptyList()
        }
    }
}