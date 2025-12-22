package com.example.flowersshop.domain.repository

import com.example.flowersshop.domain.model.ItemModel

interface ItemsRepository {
    suspend fun getItems(): List<ItemModel>
    suspend fun getItemsByCategory(category: String): List<ItemModel>
    suspend fun getItemById(itemId: String): Result<ItemModel>
}