package com.example.flowersshop.ui.signup

interface SignupEvents {

    data class OnEmailChange(val value: String): SignupEvents
    data class OnPasswordChange(val value: String): SignupEvents
    data class OnNameChange(val value: String): SignupEvents
    data object OnSignupClick: SignupEvents
    data object OnDismissDialog: SignupEvents
}