package com.example.flowersshop.ui.main

interface MainEvents {
    data class OnCategoryChange(val value: String): MainEvents
    data class OnCartClick(val value: String): MainEvents
}