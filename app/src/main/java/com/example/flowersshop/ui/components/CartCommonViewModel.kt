package com.example.flowersshop.ui.components

import androidx.lifecycle.ViewModel
import com.example.flowersshop.ui.pages.cart.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartCommonViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    fun addToCart(bouquetId: String) {
        cartRepository.add(bouquetId)
    }
}