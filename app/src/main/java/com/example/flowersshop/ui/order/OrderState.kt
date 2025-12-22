package com.example.flowersshop.ui.order

import com.example.flowersshop.domain.model.CartModel
import com.example.flowersshop.ui.pages.cart.CartItem

data class OrderState(
    val payment: String = "При получении",
    val delivery: String = "Доставка",
    val address: String = "",
    val name: String = "",
    val phone: String = "",
    val comment: String = "",
    val products: List<CartItem> = emptyList(),
    val isLoading: Boolean = true,
    val totalCoast: Long = 200,
    val isSuccess: Boolean = false
)

