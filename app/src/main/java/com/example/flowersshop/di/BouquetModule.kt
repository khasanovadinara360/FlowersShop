package com.example.flowersshop.di

import com.example.flowersshop.data.repository.ItemsRepositoryImpl
import com.example.flowersshop.domain.repository.ItemsRepository
import com.example.flowersshop.domain.usecase.GetItemsByCategory
import com.example.flowersshop.domain.usecase.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BouquetModule {

    @Provides
    @Singleton
    fun provideGetItemsUseCase(
        repo: ItemsRepository )  = GetItemsUseCase(repo)
    @Provides
    @Singleton
    fun provideGetItemsByCategory(
        repo: ItemsRepository )  = GetItemsByCategory(repo)
    @Provides
    @Singleton
    fun provideItemsRepository(
         ): ItemsRepository  = ItemsRepositoryImpl()
}