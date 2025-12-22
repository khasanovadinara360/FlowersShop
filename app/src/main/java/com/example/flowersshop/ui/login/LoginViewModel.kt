package com.example.flowersshop.ui.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersshop.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
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
            LoginEvents.OnLoginClick -> {
                viewModelScope.launch{
                    val res = loginUseCase.execute(_state.value.email, _state.value.password)
                    if (res.isSuccess) {
                        _state.value = _state.value.copy(
                            isSuccess = true
                        )
                    } else {
                        _state.value = _state.value.copy(
                            isError = true,
                            errorMessage = res.exceptionOrNull()!!.message!!
                        )
                    }
                }
            }
            LoginEvents.OnDismissDialog -> {
                _state.value = _state.value.copy(
                    isError = false
                )
            }
        }
    }
}