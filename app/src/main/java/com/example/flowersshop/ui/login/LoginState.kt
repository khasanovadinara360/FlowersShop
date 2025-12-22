package com.example.flowersshop.ui.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
