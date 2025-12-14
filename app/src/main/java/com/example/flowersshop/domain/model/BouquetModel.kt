package com.example.flowersshop.domain.model

data class BouquetModel(
    val id: String,
    val imageUrl: String,
    val title: String,
    val desc: String,
    val coast: Long,
    val category: String,
    val width: Int?,
    val height: Int?
)
