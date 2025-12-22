package com.example.flowersshop.di

import com.example.flowersshop.data.repository.OrderRepositoryImpl
import com.example.flowersshop.domain.repository.OrderRepository
import com.example.flowersshop.domain.usecase.order.AddOrderUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderModule {
    @Provides
    @Singleton
    fun provideOrderRepository(): OrderRepository = OrderRepositoryImpl()

    @Provides
    @Singleton
    fun provideAddOrderUseCase(
        repo: OrderRepository
    ) = AddOrderUseCase(repo)
}