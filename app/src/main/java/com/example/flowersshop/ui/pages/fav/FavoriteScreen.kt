package com.example.flowersshop.ui.pages.fav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flowersshop.ui.Route
import com.example.flowersshop.ui.components.ItemCard
import com.example.flowersshop.ui.components.Logo
import com.example.flowersshop.ui.theme.fonts3

@Composable
fun FavoriteScreen(navController: NavController, viewModel: FavouriteViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 25.dp)) {
        Logo(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 25.dp)
        )
        Spacer(Modifier.height(10.dp))
        Text("Избранное", fontSize = 30.sp, fontFamily = fonts3, color = Color(0xFF2B2B2B), modifier = Modifier.padding(vertical = 10.dp))
        if (state.isLoading) {
            Text("Загрузка")
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(state.products) { i ->
                    ItemCard(i.imageUrl, i.title, i.coast, action = {
                        navController.navigate(Route.BouquetCard.createRoute(i.id))
                    })
                }

            }
        }
    }
}