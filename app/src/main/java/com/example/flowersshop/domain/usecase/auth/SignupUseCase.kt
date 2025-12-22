package com.example.flowersshop.domain.usecase.auth

import com.example.flowersshop.domain.repository.AuthRepository

class SignupUseCase(
    private val repo: AuthRepository
) {
    suspend fun execute(email: String, password: String, name: String): Result<Unit> {
        return repo.signUp(email, password, name)
    }
}