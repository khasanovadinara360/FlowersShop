package com.example.flowersshop.data.dto

import com.example.flowersshop.domain.model.CustomBouquetModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomBouquetModelDto(
    val id: String? = null,
    val title: String,
    @SerialName("flower_id") val flowerId: String,
    @SerialName("green_id") val greenId: String,
    @SerialName("pack_id") val packId: String,
    @SerialName("card_id") val cardId: String,
    @SerialName("user_id") val userId: String,
    val coast: Long
)
fun CustomBouquetModelDto.toDomain(): CustomBouquetModel = (
        CustomBouquetModel(
            id = id,
            title = title,
            flowerId = flowerId,
            greenId = greenId,
            packId = packId,
            cardId = cardId,
            userId = userId,
            coast = coast
        )
        )
