package com.example.flowersshop.domain.usecase.items

import com.example.flowersshop.domain.model.ItemModel
import com.example.flowersshop.domain.repository.ItemsRepository

class GetItemByIdUseCase(
    private val repo: ItemsRepository
) {
    suspend fun execute(itemId: String): Result<ItemModel> {
        return repo.getItemById(itemId)
    }
}