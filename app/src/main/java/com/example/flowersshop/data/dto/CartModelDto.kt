package com.example.flowersshop.data.dto

import com.example.flowersshop.domain.model.CartModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartModelDto(

    val id: String? = null,
    @SerialName("user_id") val userId: String,
    @SerialName("product_id") val productId: String? = null,
    @SerialName("custom_product_id") val customProductId: String? = null,
    val volume: Int
)
fun CartModelDto.toDomain(): CartModel = (
        CartModel(
            id = id,
            userId = userId,
            productId = productId,
            customProductId = customProductId,
            volume = volume
        )
        )