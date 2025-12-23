package com.example.flowersshop.di

import com.example.flowersshop.data.repository.UserRepositoryImpl
import com.example.flowersshop.domain.repository.UserRepository
import com.example.flowersshop.domain.usecase.user.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository = UserRepositoryImpl()

    @Provides
    @Singleton
    fun provideGetUserUseCase(
        repo: UserRepository
    ) = GetUserUseCase(repo)
}