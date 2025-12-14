package com.example.flowersshop.di

import com.example.flowersshop.data.repository.BouquetsRepositoryImpl
import com.example.flowersshop.data.repository.ItemsRepositoryImpl
import com.example.flowersshop.domain.repository.BouquetsRepository
import com.example.flowersshop.domain.repository.ItemsRepository
import com.example.flowersshop.domain.usecase.GetBouquetBuildUseCase
import com.example.flowersshop.domain.usecase.GetBouquetByIdUseCase
import com.example.flowersshop.domain.usecase.GetBouquetsByCategoryUseCase
import com.example.flowersshop.domain.usecase.GetCategoriesUseCase
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
    @Provides
    @Singleton
    fun provideBouquetRepository(
         ): BouquetsRepository  = BouquetsRepositoryImpl()

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(repo: BouquetsRepository) = GetCategoriesUseCase(repo)

    @Provides
    @Singleton
    fun provideGetBouquetsByCategoryUseCase(repo: BouquetsRepository) =
        GetBouquetsByCategoryUseCase(repo)

    @Provides
    @Singleton
    fun provideGetBouquetBuildUseCase(repo: BouquetsRepository) = GetBouquetBuildUseCase(repo)

    @Provides
    @Singleton
    fun provideGetBouquetByIdUseCase(repo: BouquetsRepository) = GetBouquetByIdUseCase(repo)

}