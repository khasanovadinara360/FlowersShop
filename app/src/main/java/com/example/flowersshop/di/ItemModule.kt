package com.example.flowersshop.di

import com.example.flowersshop.data.repository.ItemsRepositoryImpl
import com.example.flowersshop.domain.repository.ItemsRepository
import com.example.flowersshop.domain.usecase.items.GetItemByIdUseCase
import com.example.flowersshop.domain.usecase.items.GetItemsByCategory
import com.example.flowersshop.domain.usecase.items.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ItemModule {

    @Provides
    @Singleton
    fun provideGetItemsUseCase(
        repo: ItemsRepository
    ) = GetItemsUseCase(repo)

    @Provides
    @Singleton
    fun provideGetItemsByCategory(
        repo: ItemsRepository
    ) = GetItemsByCategory(repo)

    @Provides
    @Singleton
    fun provideItemsRepository(
    ): ItemsRepository = ItemsRepositoryImpl()

    @Provides
    @Singleton
    fun provideGetItemByIdUseCase(
        repo: ItemsRepository
    ) = GetItemByIdUseCase(repo)
}