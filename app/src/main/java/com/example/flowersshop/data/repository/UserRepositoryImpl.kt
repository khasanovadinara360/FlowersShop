package com.example.flowersshop.data.repository

import com.example.flowersshop.data.InitSupabaseClient.client
import com.example.flowersshop.data.dto.UserModelDto
import com.example.flowersshop.data.dto.toDomain
import com.example.flowersshop.domain.model.UserModel
import com.example.flowersshop.domain.repository.UserRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest

class UserRepositoryImpl(): UserRepository {
    override suspend fun getUser(): Result<UserModel> {
        val userId = client.auth.currentUserOrNull()?.id ?: return Result.failure(
            NullPointerException("Пользователь не авторизован")
        )
        return try {
            val user = client.postgrest["users"].select {
                filter {
                    eq("id", userId)
                }
            }.decodeSingle<UserModelDto>().toDomain()
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}