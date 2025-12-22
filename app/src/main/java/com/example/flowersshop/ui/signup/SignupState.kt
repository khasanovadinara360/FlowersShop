package com.example.flowersshop.ui.signup

data class SignupState(
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val email: String = "",
    val password: String = "",
    val name: String = ""
)
