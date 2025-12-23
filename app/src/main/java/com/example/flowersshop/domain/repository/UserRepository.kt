package com.example.flowersshop.domain.repository

import com.example.flowersshop.domain.model.UserModel

interface UserRepository {
    suspend fun getUser(): Result<UserModel>
}