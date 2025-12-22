package com.example.flowersshop.di

import com.example.flowersshop.data.repository.FavouriteRepositoryImpl
import com.example.flowersshop.domain.repository.FavouriteRepository
import com.example.flowersshop.domain.usecase.favourite.AddFavouriteUseCase
import com.example.flowersshop.domain.usecase.favourite.DelFavouriteUseCase
import com.example.flowersshop.domain.usecase.favourite.GetFavouritesUseCase
import com.example.flowersshop.domain.usecase.favourite.IsBouquetFavouriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteModule {

    @Provides
    @Singleton
    fun provideAddFavouriteUseCase(
        repo: FavouriteRepository )  = AddFavouriteUseCase(repo)
    @Provides
    @Singleton
    fun provideIsFavouriteUseCase(
        repo: FavouriteRepository )  = IsBouquetFavouriteUseCase(repo)
    @Provides
    @Singleton
    fun provideDelFavouriteUseCase(
        repo: FavouriteRepository )  = DelFavouriteUseCase(repo)
    @Provides
    @Singleton
    fun provideFavouriteRepository(
    ): FavouriteRepository  = FavouriteRepositoryImpl()

    @Provides
    @Singleton
    fun provideGetFavouritesUseCase(
        repo: FavouriteRepository
    ) = GetFavouritesUseCase(repo)
}
