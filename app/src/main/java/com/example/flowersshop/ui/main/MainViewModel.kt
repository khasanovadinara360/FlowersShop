package com.example.flowersshop.ui.main

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersshop.domain.usecase.bouquets.GetBouquetsByCategoryUseCase
import com.example.flowersshop.domain.usecase.bouquets.GetCategoriesUseCase
import com.example.flowersshop.domain.usecase.cart.AddToCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBouquetsByCategoryUseCase: GetBouquetsByCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val addToCartUseCase: AddToCartUseCase
): ViewModel() {
    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    val gridState = LazyGridState()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getCategoriesUseCase.execute()
            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        categories = res.getOrNull()!!,
                        category = res.getOrNull()!![0]
                    )
                }
                getBouquetsByCategory(_state.value.category)
            }
        }
    }


    fun getBouquetsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getBouquetsByCategoryUseCase.execute(category)
            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        bouquets = res.getOrNull()!!,
                    )
                }
            }
        }
    }
    fun onEvent(event: MainEvents) {
        when (event) {
            is MainEvents.OnCategoryChange -> {
                _state.value = _state.value.copy(
                    category = event.value
                )
                getBouquetsByCategory(_state.value.category)
            }
            is MainEvents.OnCartClick -> {
                viewModelScope.launch{
                    val res = addToCartUseCase.execute(event.value, 1)
                    if (res.isSuccess) {
                        _state.value = _state.value.copy(
                            isSuccess = true
                        )
                    }
                }
            }
        }
    }
}