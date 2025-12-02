package com.example.flowersshop.ui.pages.fav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun FavouriteScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Main screen", modifier = Modifier.weight(1f))
    }
}