package com.example.flowersshop.domain.usecase

import com.example.flowersshop.domain.model.ItemModel
import com.example.flowersshop.domain.repository.ItemsRepository

class GetItemsByCategory(
    private val repo: ItemsRepository
) {
    suspend fun execute( category: String): List<ItemModel> {
        return repo.getItemsByCategory(category)
    }
}