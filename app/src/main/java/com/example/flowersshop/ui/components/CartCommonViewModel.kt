package com.example.flowersshop.ui.components

import androidx.lifecycle.ViewModel
import com.example.flowersshop.domain.repository.LocalCartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartCommonViewModel @Inject constructor(
    private val cartRepository: LocalCartRepository
) : ViewModel() {

    fun addToCart(bouquetId: String) {
        cartRepository.add(bouquetId)
    }
}