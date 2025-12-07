package com.example.flowersshop.ui.pages.bouquet

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersshop.domain.usecase.GetItemsByCategory
import com.example.flowersshop.domain.usecase.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BouquetViewModel @Inject constructor(
    private val getItems: GetItemsUseCase,
    private val getItemsByCategory: GetItemsByCategory
): ViewModel() {
    private val _state = mutableStateOf(BouquetState())
    val state: State<BouquetState> = _state

    init {
        loadItems()
    }
    fun loadItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val flowers = getItemsByCategory.execute("flowers")
            val greens = getItemsByCategory.execute("greens")
            val packs = getItemsByCategory.execute("packs")
            val cards = getItemsByCategory.execute("cards")
            _state.value = _state.value.copy(
                flowers = flowers,
                greens = greens,
                packs = packs,
                cards = cards,
                isLoading = false
            )
        }
    }

    fun onEvent(event: BouquetEvents) {
        when (event) {
            is BouquetEvents.OnFlowerClick -> {
                _state.value = _state.value.copy(
                    flower = event.value,
                    page = _state.value.page + 1
                )
            }
            is BouquetEvents.OnGreenClick -> {
                _state.value = _state.value.copy(
                    green = event.value,
                    page = _state.value.page + 1
                )
            }
            is BouquetEvents.OnPackClick -> {
                _state.value = _state.value.copy(
                    pack = event.value,
                    page = _state.value.page + 1
                )
            }
            is BouquetEvents.OnCardClick -> {
                _state.value = _state.value.copy(
                    card = event.value,
                    page = _state.value.page + 1
                )
            }
        }
    }
}