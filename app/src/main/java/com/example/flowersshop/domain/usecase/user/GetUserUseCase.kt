package com.example.flowersshop.domain.usecase.user

import com.example.flowersshop.domain.model.UserModel
import com.example.flowersshop.domain.repository.UserRepository

class GetUserUseCase(
    private val repo: UserRepository
) {
    suspend fun execute(): Result<UserModel> {
        return repo.getUser()
    }
}