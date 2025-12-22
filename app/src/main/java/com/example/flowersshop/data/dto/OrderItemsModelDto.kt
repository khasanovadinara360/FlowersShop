package com.example.flowersshop.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderItemsModelDto(
    val id: String? = null,
    @SerialName("order_id") val orderId: String,
    @SerialName("product_id") val productId: String? = null,
    @SerialName("custom_product_id") val customProductId: String? = null,

)
