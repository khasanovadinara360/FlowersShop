package com.example.flowersshop.ui.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersshop.domain.model.CartModel
import com.example.flowersshop.domain.model.ItemModel
import com.example.flowersshop.domain.repository.LocalCartRepository
import com.example.flowersshop.domain.usecase.bouquets.GetBouquetByIdUseCase
import com.example.flowersshop.domain.usecase.bouquets.GetBuildBouquetUseCase
import com.example.flowersshop.domain.usecase.cart.ClearCartUseCase
import com.example.flowersshop.domain.usecase.cart.GetCartUseCase
import com.example.flowersshop.domain.usecase.cart.UpdateCartUseCase
import com.example.flowersshop.domain.usecase.items.GetItemByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val getBouquetByIdUseCase: GetBouquetByIdUseCase,
    private val getBuildBouquetUseCase: GetBuildBouquetUseCase,
    private val getItemByIdUseCase: GetItemByIdUseCase,
    private val cartRepository: LocalCartRepository,
    private val updateCartUseCase: UpdateCartUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {
    private val _state = mutableStateOf(CartState())
    val state: State<CartState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getCartUseCase.execute()
            if (res.isSuccess) {
                res.getOrNull()!!.forEach { i ->
                    if (i.customProductId == null) {
                        val product = getBouquetByIdUseCase.execute(i.productId!!)
                        if (product.isSuccess) {
                            val prod = product.getOrNull()!!
                            withContext(Dispatchers.Main) {
                                _state.value = _state.value.copy(
                                    products = _state.value.products + CartItem.CartBouquet(
                                        prod.id,
                                        prod.title,
                                        prod.coast,
                                        volume = i.volume
                                    ),
                                    totalCoast = _state.value.totalCoast + i.volume * prod.coast
                                )
                            }
                        }
                    } else {
                        val customProducts = getBuildBouquetUseCase.execute(i.customProductId)
                        if (customProducts.isSuccess) {
                            val prod = customProducts.getOrNull()!!
                            var comps = emptyList<ItemModel>()
                            val item1 = getItemByIdUseCase.execute(prod.flowerId).getOrNull()
                            val item2 = getItemByIdUseCase.execute(prod.greenId).getOrNull()
                            val item3 = getItemByIdUseCase.execute(prod.packId).getOrNull()
                            val item4 = getItemByIdUseCase.execute(prod.cardId).getOrNull()

                            if (item1 != null && item2 != null && item3 != null && item4 != null) {
                                comps = comps + item1 + item2 + item3 + item4
                            }
                            withContext(Dispatchers.Main) {
                                _state.value = _state.value.copy(
                                    products = _state.value.products + CartItem.CustomBouquet(
                                        prod.id!!,
                                        prod.title,
                                        prod.coast,
                                        comps,
                                        volume = i.volume
                                    ),
                                    totalCoast = _state.value.totalCoast + i.volume * prod.coast
                                )
                            }
                        }
                    }
                }
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                }
            }
        }
    }


    suspend fun updateCart() {
        val list = mutableListOf<CartModel>()
        _state.value.products.forEach { i ->
            list.add(
                when (i) {
                    is CartItem.CartBouquet -> {
                        CartModel(
                            userId = "",
                            productId = i.id,
                            volume = i.volume
                        )
                    }

                    is CartItem.CustomBouquet -> {
                        CartModel(
                            userId = "",
                            customProductId = i.id,
                            volume = i.volume
                        )
                    }
                }
            )
        }
        withContext(Dispatchers.IO) {
            val res = updateCartUseCase.execute(list)
        }
    }

    fun onEvent(event: CartEvents) {
        when (event) {
            is CartEvents.AddClick -> {
                val prod = _state.value.products[event.index]
                when (prod) {
                    is CartItem.CartBouquet -> {
                        cartRepository.add(prod.id)
                        val currentList = _state.value.products.toMutableList()
                        val updated = prod.copy(volume = prod.volume + 1)
                        currentList[event.index] = updated
                        _state.value = _state.value.copy(
                            products = currentList,
                            totalCoast = _state.value.totalCoast + prod.coast
                        )
                    }
                    is CartItem.CustomBouquet -> {
                        cartRepository.add(prod.id)
                        val currentList = _state.value.products.toMutableList()
                        val updated = prod.copy(volume = prod.volume + 1)
                        currentList[event.index] = updated
                        _state.value = _state.value.copy(
                            products = currentList,
                            totalCoast = _state.value.totalCoast + prod.coast
                        )
                    }
                }
            }
            is CartEvents.DelClick -> {
                val currentList = _state.value.products.toMutableList()
                val item = currentList[event.index]
                when (item) {
                    is CartItem.CartBouquet -> {
                        cartRepository.remove(item.id)
                        if (item.volume > 1) {
                            val updated = item.copy(volume = item.volume - 1)
                            currentList[event.index] = updated
                            _state.value = _state.value.copy(
                                products = currentList,
                                totalCoast = _state.value.totalCoast - item.coast
                            )
                        } else {
                            currentList.removeAt(event.index)
                            _state.value = _state.value.copy(
                                products = currentList,
                                totalCoast = _state.value.totalCoast - item.coast
                            )
                        }
                    }

                    is CartItem.CustomBouquet -> {
                        cartRepository.remove(item.id)
                        if (item.volume > 1) {
                            val updated = item.copy(volume = item.volume - 1)
                            currentList[event.index] = updated
                            _state.value = _state.value.copy(
                                products = currentList,
                                totalCoast = _state.value.totalCoast - item.coast
                            )
                        } else {
                            currentList.removeAt(event.index)
                            _state.value = _state.value.copy(
                                products = currentList,
                                totalCoast = _state.value.totalCoast - item.coast
                            )
                        }
                    }
                }
            }
            CartEvents.ClearClick -> {
                viewModelScope.launch {
                    val res = clearCartUseCase.execute()
                    if (res.isSuccess) {
                        cartRepository.clear()
                        _state.value = _state.value.copy(
                            products = emptyList()
                        )
                    }
                }
            }
            CartEvents.OrderClick -> {
//                if (_state.value.products.isEmpty()) {
                    _state.value = _state.value.copy(
                        errorMessage = "Корзина пуста",
                        isError = _state.value.products.isEmpty(),
                        isSuccess = !_state.value.isError
                    )
//                }
            }
            CartEvents.DismissClick -> {
                _state.value = _state.value.copy(
                    isError = false,
                )
            }
        }
    }
}