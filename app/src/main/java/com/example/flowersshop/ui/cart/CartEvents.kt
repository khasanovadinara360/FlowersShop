package com.example.flowersshop.ui.cart

interface CartEvents {
    data class AddClick(val index: Int): CartEvents
    data class DelClick(val index: Int): CartEvents
    data object ClearClick: CartEvents
    data object OrderClick: CartEvents
    data object DismissClick: CartEvents

}