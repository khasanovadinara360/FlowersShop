package com.example.flowersshop.domain.usecase

import com.example.flowersshop.domain.repository.BouquetsRepository

class GetCategoriesUseCase(
    private val repo: BouquetsRepository
) {
    suspend fun execute(): Result<List<String>> {
        return repo.getCategories()
    }
}