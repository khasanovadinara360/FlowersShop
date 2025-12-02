package com.example.flowersshop.domain.usecase

import com.example.flowersshop.domain.model.ItemModel
import com.example.flowersshop.domain.repository.ItemsRepository

class GetItemsUseCase(private val repo: ItemsRepository
) {
    suspend fun execute(): List<ItemModel> {
        return repo.getItems()
    }
}