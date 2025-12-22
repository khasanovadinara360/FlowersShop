package com.example.flowersshop.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavouriteModelDto(
    @SerialName("product_id") val productId: String,
    @SerialName("user_id") val userId: String,
)
