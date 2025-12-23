package com.example.flowersshop.data.dto

import com.example.flowersshop.domain.model.UserModel
import kotlinx.serialization.Serializable

@Serializable
data class UserModelDto(
    val id: String,
    val name: String
)

fun UserModelDto.toDomain(): UserModel = (
        UserModel(
            id = id, name = name
        )
        )
