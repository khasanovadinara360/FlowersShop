package com.example.flowersshop.domain.model

data class CustomBouquetModel(
    val id: String? = null,
    val title: String,
    val flowerId: String,
    val greenId: String,
    val packId: String,
    val cardId: String,
    val userId: String,
    val coast: Long
)
