package com.example.flowersshop.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderModelDto(
    val id: String? = null,
    @SerialName("user_id") val userId: String,
    @SerialName("total_price") val totalPrice: Long,
    val address: String,
    val payment: String,
    val delivery: String,
    val phone: String,
    val comment: String,

    )
