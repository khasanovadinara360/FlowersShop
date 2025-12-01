package com.example.flowersshop.ui.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> = _state
    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            _state.value = _state.value.copy(
                isTimeOut = true
            )
        }
    }
}