package com.example.flowersshop.di

import com.example.flowersshop.data.repository.BouquetsRepositoryImpl
import com.example.flowersshop.domain.repository.BouquetsRepository
import com.example.flowersshop.domain.usecase.bouquets.AddBuildBouquetUseCase
import com.example.flowersshop.domain.usecase.bouquets.GetBouquetBuildUseCase
import com.example.flowersshop.domain.usecase.bouquets.GetBouquetByIdUseCase
import com.example.flowersshop.domain.usecase.bouquets.GetBouquetsByCategoryUseCase
import com.example.flowersshop.domain.usecase.bouquets.GetBuildBouquetUseCase
import com.example.flowersshop.domain.usecase.bouquets.GetCategoriesUseCase
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
    @Provides
    @Singleton
    fun provideAddBuildBouquetUseCase(
        repo: BouquetsRepository
    ) = AddBuildBouquetUseCase(repo)
    @Provides
    @Singleton
    fun provideGetBuildBouquetUseCase(
        repo: BouquetsRepository
    ) = GetBuildBouquetUseCase(repo)

}