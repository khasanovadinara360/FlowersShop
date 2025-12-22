package com.example.flowersshop.ui.bouquet

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersshop.domain.usecase.bouquets.GetBouquetByIdUseCase
import com.example.flowersshop.domain.usecase.cart.AddToCartUseCase
import com.example.flowersshop.domain.usecase.favourite.AddFavouriteUseCase
import com.example.flowersshop.domain.usecase.favourite.DelFavouriteUseCase
import com.example.flowersshop.domain.usecase.favourite.IsBouquetFavouriteUseCase
import com.example.flowersshop.ui.pages.cart.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class BouquetCardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBouquetByIdUseCase: GetBouquetByIdUseCase,
    private val addFavouriteUseCase: AddFavouriteUseCase,
    private val isBouquetFavouriteUseCase: IsBouquetFavouriteUseCase,
    private val delFavouriteUseCase: DelFavouriteUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _state = mutableStateOf(BouquetCardState())
    val state: State<BouquetCardState> = _state

    private val bouquetId: String =
        savedStateHandle["bouquetId"]!!

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getBouquetByIdUseCase.execute(bouquetId)
            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        bouquet = res.getOrNull(),
                    )
                }
            }
            val resFav = isBouquetFavouriteUseCase.execute(_state.value.bouquet!!.id)
            withContext(Dispatchers.Main) {
                _state.value = _state.value.copy(
                    initialFavourite = resFav.getOrNull() == true,
                    isFavourite = resFav.getOrNull() == true,
                    isLoading = false
                )
            }
        }
    }

    fun onEvent(event: BouquetCardEvents) {
        when (event) {
            BouquetCardEvents.OnAddClick -> {
                _state.value = _state.value.copy(
                    count = _state.value.count + 1
                )
            }

            BouquetCardEvents.OnDelClick -> {
                if (_state.value.count > 0)
                    _state.value = _state.value.copy(
                        count = _state.value.count - 1
                    )
            }

            is BouquetCardEvents.OnFavouriteClick -> {
                _state.value = _state.value.copy(
                    isFavourite = !_state.value.isFavourite
                )
            }

            is BouquetCardEvents.OnBackClick -> {
                if (_state.value.initialFavourite != _state.value.isFavourite) {
                    if (_state.value.isFavourite) {
                        viewModelScope.launch {
                            addFavouriteUseCase.execute(_state.value.bouquet!!.id)
                        }
                    } else {
                        viewModelScope.launch {
                            delFavouriteUseCase.execute(_state.value.bouquet!!.id)
                        }
                    }
                }
            }

            BouquetCardEvents.OnCartClick -> {
                if (_state.value.count > 0) {
                    viewModelScope.launch {
                        val res = addToCartUseCase.execute(_state.value.bouquet!!.id, _state.value.count)
                        if (res.isSuccess) {
                            cartRepository.loadCart()
                            _state.value = _state.value.copy(
                                isSuccess = true
                            )
                        }
                    }
//                    cartRepository.add(_state.value.bouquet!!.id, _state.value.count)
                }
            }
        }
    }
}