package com.example.flowersshop.ui.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowersshop.domain.usecase.auth.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase

) : ViewModel() {
    private val _state = mutableStateOf(SignupState())
    val state: State<SignupState> = _state

    fun onEvent(event: SignupEvents) {
        when (event) {
            is SignupEvents.OnNameChange -> {
                _state.value = _state.value.copy(
                    name = event.value
                )
            }

            is SignupEvents.OnEmailChange -> {
                _state.value = _state.value.copy(
                    email = event.value
                )
            }

            is SignupEvents.OnPasswordChange -> {
                _state.value = _state.value.copy(
                    password = event.value
                )
            }

            SignupEvents.OnSignupClick -> {
                viewModelScope.launch {
                    val res = signupUseCase.execute(
                        _state.value.email,
                        _state.value.password,
                        _state.value.name,
                    )
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
            SignupEvents.OnDismissDialog -> {
                _state.value = _state.value.copy(
                    isError = false
                )
            }
        }
    }
}