package com.example.flowersshop.ui.order

import com.example.flowersshop.ui.pages.cart.CartEvents

interface OrderEvents {

    data class OnAddressChange(val address: String): OrderEvents
    data class OnPaymentClick(val payment: String): OrderEvents
    data class OnDeliveryClick(val delivery: String): OrderEvents
    data class OnNameChange(val name: String): OrderEvents
    data class OnPhoneChange(val phone: String): OrderEvents
    data class OnCommentChange(val comment: String): OrderEvents
    data class AddClick(val index: Int): OrderEvents
    data class DelClick(val index: Int): OrderEvents
    data class OnOrderClick(val index: Int): OrderEvents
}