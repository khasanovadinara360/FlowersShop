package com.example.flowersshop.data.repository

import com.example.flowersshop.data.InitSupabaseClient.client
import com.example.flowersshop.domain.repository.AuthRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest

class AuthRepositoryImpl(): AuthRepository {
    override suspend fun signIn(email: String, password: String): Result<Unit> {
        return try {
            client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(email: String, password: String, name: String, ): Result<Unit> {
        return try {
            val res =  client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
//            if (res.is)
            val user = client.auth.currentUserOrNull()
            if (user != null) {
                client.postgrest["users"].insert(
                    mapOf(
                        "id" to user.id,
                        "name" to name
                    )
                )
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}