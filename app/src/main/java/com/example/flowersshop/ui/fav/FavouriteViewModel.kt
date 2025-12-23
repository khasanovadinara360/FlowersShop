package com.example.flowersshop.ui.fav

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersshop.domain.usecase.favourite.GetFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase
): ViewModel() {
    private val _state = mutableStateOf(FavouriteState())
    val state: State<FavouriteState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getFavouritesUseCase.execute()
            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        products = res.getOrNull()!!,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onEvent(event: FavouriteEvents) {
        when (event) {

        }
    }
}