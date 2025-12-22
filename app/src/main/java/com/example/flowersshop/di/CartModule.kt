package com.example.flowersshop.di

import com.example.flowersshop.data.repository.CartRepositoryImpl
import com.example.flowersshop.domain.repository.CartRepository
import com.example.flowersshop.domain.usecase.cart.AddBuildToCartUseCase
import com.example.flowersshop.domain.usecase.cart.AddToCartUseCase
import com.example.flowersshop.domain.usecase.cart.ClearCartUseCase
import com.example.flowersshop.domain.usecase.cart.GetCartUseCase
import com.example.flowersshop.domain.usecase.cart.UpdateCartUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartModule {
    @Provides
    @Singleton
    fun provideCartRepository(): CartRepository = CartRepositoryImpl()

    @Provides
    @Singleton
    fun provideAddToCartUseCase(
        repo: CartRepository
    ) = AddToCartUseCase(repo)

    @Provides
    @Singleton
    fun provideAddBuildToCartUseCase(
        repo: CartRepository
    ) = AddBuildToCartUseCase(repo)

    @Provides
    @Singleton
    fun provideGetCartUseCase(
        repo: CartRepository
    ) = GetCartUseCase(repo)

    @Provides
    @Singleton
    fun provideeUpdateCartUseCase(
        repo: CartRepository
    ) = UpdateCartUseCase(repo)
    @Provides
    @Singleton
    fun provideClearCartUseCase(
        repo: CartRepository
    ) = ClearCartUseCase(repo)
}