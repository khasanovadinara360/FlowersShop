package com.example.flowersshop.ui.login

interface LoginEvents {

    data class OnEmailChange(val value: String): LoginEvents
    data class OnPasswordChange(val value: String): LoginEvents
}