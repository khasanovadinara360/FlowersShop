package com.example.flowersshop.ui.pages.bouquet

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersshop.domain.usecase.GetBouquetBuildUseCase
import com.example.flowersshop.domain.usecase.GetItemsByCategory
import com.example.flowersshop.domain.usecase.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BouquetViewModel @Inject constructor(
    private val getItems: GetItemsUseCase,
    private val getItemsByCategory: GetItemsByCategory,
    private val getBouquetBuildUseCase: GetBouquetBuildUseCase
) : ViewModel() {
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
    fun getBouquetBuild(flowerId: String, greenId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getBouquetBuildUseCase.execute(flowerId, greenId)
            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        resBouquetUrl = res.getOrNull()!!
                    )
                }
            }
        }
    }

    fun onEvent(event: BouquetEvents) {
        when (event) {
            is BouquetEvents.OnFlowerClick -> {
                _state.value = _state.value.copy(
//                    flower = event.value,
                    items = _state.value.items + event.value,
                    coast = _state.value.coast + event.value.coast,
                    page = _state.value.page + 1

                )
            }

            is BouquetEvents.OnGreenClick -> {
                _state.value = _state.value.copy(
//                    green = event.value,
                    items = _state.value.items + event.value,
                    coast = _state.value.coast + event.value.coast,
                    page = _state.value.page + 1
                )
                getBouquetBuild(_state.value.items[0].id, _state.value.items[1].id)
            }

            is BouquetEvents.OnPackClick -> {
                _state.value = _state.value.copy(
//                    pack = event.value,
                    items = _state.value.items + event.value,
                    coast = _state.value.coast + event.value.coast,
                    page = _state.value.page + 1
                )
            }

            is BouquetEvents.OnCardClick -> {
                _state.value = _state.value.copy(
//                    card = event.value,
                    items = _state.value.items + event.value,
                    coast = _state.value.coast + event.value.coast,
                    page = _state.value.page + 1
                )
            }

            is BouquetEvents.OnAddItemClick -> {
                when (event.item.category) {
                    "flowers" -> {
                        _state.value = _state.value.copy(
                            flowersCount = _state.value.flowersCount + 1,
                            coast = _state.value.coast + event.item.coast * _state.value.flowersCount
                        )
                    }

                    "greens" -> {
                        _state.value = _state.value.copy(
                            greensCount = _state.value.greensCount + 1,
                            coast = _state.value.coast + event.item.coast * _state.value.greensCount
                        )
                    }

                    "packs" -> {
                        _state.value = _state.value.copy(
                            packsCount = _state.value.packsCount + 1,
                            coast = _state.value.coast + event.item.coast * _state.value.packsCount
                        )
                    }

                    "cards" -> {
                        _state.value = _state.value.copy(
                            cardsCount = _state.value.cardsCount + 1,
                            coast = _state.value.coast + event.item.coast * _state.value.cardsCount
                        )
                    }
                }
            }

            is BouquetEvents.OnDelItemClick -> {
                when (event.item.category) {
                    "flowers" -> {
                        if (_state.value.flowersCount > 1) {
                            _state.value = _state.value.copy(
                                flowersCount = _state.value.flowersCount - 1,
                                coast = _state.value.coast + event.item.coast * _state.value.flowersCount
                            )
                        }
                    }

                    "greens" -> {
                        if (_state.value.greensCount > 1) {
                            _state.value = _state.value.copy(
                                greensCount = _state.value.greensCount - 1,
                                coast = _state.value.coast + event.item.coast * _state.value.greensCount

                            )
                        }
                    }

                    "packs" -> {
                        if (_state.value.packsCount > 1) {
                            _state.value = _state.value.copy(
                                packsCount = _state.value.packsCount - 1,
                                coast = _state.value.coast + event.item.coast * _state.value.packsCount

                            )
                        }
                    }

                    "cards" -> {
                        if (_state.value.cardsCount > 1) {
                            _state.value = _state.value.copy(
                                cardsCount = _state.value.cardsCount - 1,
                                coast = _state.value.coast + event.item.coast * _state.value.cardsCount

                            )
                        }
                    }
                }
            }
        }
    }
}