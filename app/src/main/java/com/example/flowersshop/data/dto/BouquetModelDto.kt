package com.example.flowersshop.data.dto

import com.example.flowersshop.domain.model.BouquetModel
import kotlinx.serialization.Serializable

@Serializable
data class BouquetModelDto(
    val id: String,
    val imageUrl: String,
    val title: String,
    val desc: String,
    val coast: Long,
    val category: String,
    val width: Int?,
    val height: Int?
)

fun BouquetModelDto.toDomain(): BouquetModel = (
        BouquetModel(
            id = id,
            title = title,
            desc = desc,
            imageUrl = imageUrl,
            coast = coast,
            category = category,
            width = width,
            height = height
        )
        )
