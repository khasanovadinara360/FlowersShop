package com.example.flowersshop.ui.pages.cart

interface CartEvents {
    data class AddClick(val index: Int): CartEvents
    data class DelClick(val index: Int): CartEvents
    data object ClearClick: CartEvents

}