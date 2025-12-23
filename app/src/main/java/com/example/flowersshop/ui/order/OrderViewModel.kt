package com.example.flowersshop.ui.order

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersshop.domain.usecase.bouquets.GetBouquetByIdUseCase
import com.example.flowersshop.domain.usecase.bouquets.GetBuildBouquetUseCase
import com.example.flowersshop.domain.usecase.cart.GetCartUseCase
import com.example.flowersshop.domain.usecase.order.AddOrderUseCase
import com.example.flowersshop.ui.cart.CartItem
import com.example.flowersshop.domain.repository.LocalCartRepository
import com.example.flowersshop.domain.usecase.user.GetUserUseCase
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
    private val cartRepository: LocalCartRepository,
    private val addOrderUseCase: AddOrderUseCase,
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private val _state = mutableStateOf(OrderState())
    val state: State<OrderState> = _state

//    val products = savedStateHandle.getStateFlow("products", emptyList<CartItem>())

    init {
//        val products = savedStateHandle.get<List<CartItem>>("products") ?: emptyList()
//        _state.value = _state.value.copy(
//            products = products,
//            isLoading = false
//        )


        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.execute()
            if (user.isSuccess) {
                _state.value = _state.value.copy(
                    name = user.getOrNull()!!.name
                )
            } else {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = user.exceptionOrNull()!!.message.toString()
                )
            }
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
                    val listProducts: List<String> = _state.value.products
                        .filterIsInstance<CartItem.CartBouquet>()
                        .map { it.id }

                    val listCustomProducts: List<String> = _state.value.products
                        .filterIsInstance<CartItem.CustomBouquet>()
                        .map { it.id }

                    if (_state.value.address.isEmpty()) {
                        _state.value = _state.value.copy(
                            isError = true,
                            errorMessage = "Заполните адрес доставки"
                        )
                    } else {
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
                        _state.value = _state.value.copy(
                            isSuccess = res.isSuccess
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
                        if (event.delivery == "Самовывоз" && event.delivery != _state.value.delivery) {
                            _state.value.totalCoast - 200
                        } else {
                            _state.value.totalCoast
                        }
                    }
                )
            }

            OrderEvents.OnDismissClick -> {
                _state.value = _state.value.copy(
                    isError = false
                )
            }
        }
    }
}