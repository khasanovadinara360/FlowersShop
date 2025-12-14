package com.example.flowersshop.ui.pages.main

interface MainEvents {
    data class OnCategoryChange(val value: String): MainEvents
}