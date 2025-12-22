package com.example.flowersshop.domain.usecase.auth

import com.example.flowersshop.domain.repository.AuthRepository

class LoginUseCase(
    private val repo: AuthRepository
) {
    suspend fun execute(email: String, password: String): Result<Unit> {
        return repo.signIn(email, password)
    }
}