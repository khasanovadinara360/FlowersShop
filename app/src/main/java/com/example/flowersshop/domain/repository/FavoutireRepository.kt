package com.example.flowersshop.domain.repository

import com.example.flowersshop.domain.model.BouquetModel

interface FavouriteRepository {
    suspend fun addFavourite(productId: String): Result<Unit>
    suspend fun delFavourite(productId: String): Result<Unit>
    suspend fun isFavourite(productId: String): Result<Boolean>
    suspend fun getFavourites(): Result<List<BouquetModel>>
}