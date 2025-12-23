package com.example.flowersshop.domain.repository

import android.util.Log
import com.example.flowersshop.domain.usecase.cart.GetCartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalCartRepository @Inject constructor(
    private val getCartUseCase: GetCartUseCase
) {

    private val _items = MutableStateFlow<Map<String, Int>>(emptyMap())
    val items = _items.asStateFlow()

    suspend fun loadCart() {
        val res = getCartUseCase.execute()
        if (res.isSuccess) {
            val newItems = mutableMapOf<String, Int>()
            res.getOrNull()?.forEach { i ->
                val id = i.productId ?: i.customProductId!!
                newItems[id] = i.volume
            }
            _items.value = newItems
        }

    }

    fun add(productId: String, volume: Int = 1) {
        _items.value = _items.value.toMutableMap().apply {
            put(productId, (get(productId) ?: 0) + volume)
        }
        Log.d("CartRepository", "add(): $productId, $volume -> new: ${_items.value[productId]}")

    }

    fun remove(productId: String) {
        _items.value = _items.value.toMutableMap().apply {
            val count = get(productId) ?: return
            if (count <= 1) remove(productId)
            else put(productId, count - 1)
        }
    }

    fun totalCount(): Int = _items.value.values.sum()
    fun clear() {
        _items.value = emptyMap()
    }
}
