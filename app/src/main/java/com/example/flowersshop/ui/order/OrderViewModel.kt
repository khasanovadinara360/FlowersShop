package com.example.flowersshop.ui.pages.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersshop.domain.model.BouquetModel
import com.example.flowersshop.domain.model.ItemModel
import com.example.flowersshop.domain.usecase.bouquets.GetBouquetByIdUseCase
import com.example.flowersshop.domain.usecase.bouquets.GetBuildBouquetUseCase
import com.example.flowersshop.domain.usecase.cart.ClearCartUseCase
import com.example.flowersshop.domain.usecase.cart.GetCartUseCase
import com.example.flowersshop.domain.usecase.cart.UpdateCartUseCase
import com.example.flowersshop.domain.usecase.items.GetItemByIdUseCase
import com.example.flowersshop.domain.usecase.order.AddOrderUseCase
import com.example.flowersshop.ui.order.OrderEvents
import com.example.flowersshop.ui.order.OrderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val getBouquetByIdUseCase: GetBouquetByIdUseCase,
    private val getBuildBouquetUseCase: GetBuildBouquetUseCase,
    private val cartRepository: CartRepository,
    private val addOrderUseCase: AddOrderUseCase
) : ViewModel() {
    private val _state = mutableStateOf(OrderState())
    val state: State<OrderState> = _state

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
                            withContext(Dispatchers.Main) {
                                _state.value = _state.value.copy(
                                    products = _state.value.products + CartItem.CustomBouquet(
                                        prod.id!!,
                                        prod.title,
                                        prod.coast,
                                        emptyList(),
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

    fun onEvent(event: OrderEvents) {
        when (event) {

            is OrderEvents.OnAddressChange -> {
                _state.value = _state.value.copy(
                    address = event.address
                )
            }

            is OrderEvents.OnNameChange -> {
                _state.value = _state.value.copy(
                    name = event.name
                )
            }

            is OrderEvents.OnPhoneChange -> {
                _state.value = _state.value.copy(
                    phone = event.phone
                )
            }

            is OrderEvents.OnCommentChange -> {
                _state.value = _state.value.copy(
                    comment = event.comment
                )
            }

            is OrderEvents.AddClick -> {
                val prod = _state.value.products[event.index]

                when (prod) {
                    is CartItem.CartBouquet -> {
                        val currentList = _state.value.products.toMutableList()
                        val updated = prod.copy(volume = prod.volume + 1)
                        currentList[event.index] = updated
                        _state.value = _state.value.copy(
                            products = currentList,
                            totalCoast = _state.value.totalCoast + prod.coast
                        )
                    }

                    is CartItem.CustomBouquet -> {
                        val currentList = _state.value.products.toMutableList()
                        val updated = prod.copy(volume = prod.volume + 1)
                        currentList[event.index] = updated
                        _state.value = _state.value.copy(
                            products = currentList,
                            totalCoast = _state.value.totalCoast + prod.coast
                        )
                    }
//                    is CartItem.CartBouquet -> {
//                        _state.value = _state.value.copy(
//                            totalCoast = _state.value.totalCoast + prod.coast
//                        )
//                    }
//                    is CartItem.CustomBouquet -> {
//                        _state.value = _state.value.copy(
//                            totalCoast = _state.value.totalCoast + prod.coast
//                        )
//                    }
                }

            }

            is OrderEvents.DelClick -> {
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

            is OrderEvents.OnOrderClick -> {
                viewModelScope.launch {

//                    val listProducts: List<String> = _state.value.products.map { i ->
//                        when (i) {
//                            is CartItem.CartBouquet -> {
//                                i.id
//                            }
//                            is CartItem.CustomBouquet -> {
//                                i.id
//                            }
//                        }
//                    }
                    val listProducts: List<String> = _state.value.products
                        .filterIsInstance<CartItem.CartBouquet>()
                        .map { it.id }

                    val listCustomProducts: List<String> = _state.value.products
                        .filterIsInstance<CartItem.CustomBouquet>()
                        .map { it.id }

                    val res = addOrderUseCase.execute(
                        _state.value.totalCoast,
                        _state.value.address,
                        _state.value.payment,
                        _state.value.delivery,
                        _state.value.phone,
                        _state.value.comment,
                        listProducts,
                        listCustomProducts,


                        )
                    if (res.isSuccess) {
                        _state.value = _state.value.copy(
                            isSuccess = true
                        )
                    }
                }
            }

            is OrderEvents.OnPaymentClick -> {
                _state.value = _state.value.copy(
                    payment = event.payment
                )
            }

            is OrderEvents.OnDeliveryClick -> {
                _state.value = _state.value.copy(
                    delivery = event.delivery,
                    totalCoast = if (event.delivery == "Доставка" && event.delivery != _state.value.delivery) {
                        _state.value.totalCoast + 200
                    } else {
                        if (event.delivery == "Самовывоз" && event.delivery != _state.value.delivery){
                            _state.value.totalCoast - 200
                        }
                        else {
                            _state.value.totalCoast
                        }
                    }
                )
            }
        }
    }
}