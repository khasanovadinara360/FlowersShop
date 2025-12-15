package com.example.flowersshop.ui.bouquet

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersshop.domain.usecase.GetBouquetByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class BouquetCardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getBouquetByIdUseCase: GetBouquetByIdUseCase
): ViewModel() {

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
                        isLoading = false
                    )
                }
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
        }
    }
}