package com.example.flowersshop.ui.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(

): ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun onEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.OnEmailChange -> {
                _state.value = _state.value.copy(
                    email = event.value
                )
            }
            is LoginEvents.OnPasswordChange -> {
                _state.value = _state.value.copy(
                    password = event.value
                )
            }

        }
    }
}