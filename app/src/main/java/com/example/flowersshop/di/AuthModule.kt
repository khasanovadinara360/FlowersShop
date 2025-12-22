package com.example.flowersshop.di

import com.example.flowersshop.data.repository.AuthRepositoryImpl
import com.example.flowersshop.domain.repository.AuthRepository
import com.example.flowersshop.domain.usecase.auth.LoginUseCase
import com.example.flowersshop.domain.usecase.auth.SignupUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

    @Provides
    @Singleton
    fun provideLoginUseCase( repo: AuthRepository) = LoginUseCase(repo)
    @Provides
    @Singleton
    fun provideSignupUseCase( repo: AuthRepository) = SignupUseCase(repo)
}