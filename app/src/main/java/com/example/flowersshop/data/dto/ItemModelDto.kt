package com.example.flowersshop.data.dto

import com.example.flowersshop.domain.model.ItemModel
import kotlinx.serialization.Serializable
@Serializable
data class ItemModelDto(
    val id: String,
    val title: String,
    val coast: Long,
    val category: String,
    val imageUrl: String,
)

fun ItemModelDto.toDomain(): ItemModel =
    ItemModel(id = id, category = category, title = title, coast = coast, imageUrl = imageUrl)

