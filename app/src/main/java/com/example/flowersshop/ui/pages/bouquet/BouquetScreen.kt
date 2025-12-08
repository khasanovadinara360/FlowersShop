package com.example.flowersshop.ui.pages.bouquet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flowersshop.R
import com.example.flowersshop.ui.components.ItemCard
import com.example.flowersshop.ui.components.Logo
import com.example.flowersshop.ui.theme.fonts3
import com.example.flowersshop.ui.theme.fonts4

data class Item(
    val image: Int,
    val title: String,
    val coast: Long
)

data class Page(
    val num: String,
    val title: String,
    val category: String? = null
//    val items: List<Item>? = null,
)


@Composable
fun BouquetScreen(navController: NavController, viewModel: BouquetViewModel = hiltViewModel()) {
    val state = viewModel.state.value
//    val page = remember { mutableStateOf(0) }
    val items = listOf(
        Page(
            "01", "Выберите цветок", "flowers"
            //listOf(
//                R.drawable.flower1,
//                R.drawable.flower2,
//                R.drawable.flower3,
//                R.drawable.flower4,
//                Item(R.drawable.flower1, state.flowers[0].title, state.flowers[0].coast),
//                Item(R.drawable.flower2, state.flowers[1].title, state.flowers[1].coast),
//                Item(R.drawable.flower3, state.flowers[2].title, state.flowers[2].coast),
//                Item(R.drawable.flower4, state.flowers[3].title, state.flowers[3].coast),
            // )
        ),
        Page(
            "02", "Выберите зелень", "greens"
//            listOf(
//
//                Item(R.drawable.green1, state.greens[0].title, state.greens[0].coast),
//                Item(R.drawable.green2, state.greens[1].title, state.greens[1].coast),
//                Item(R.drawable.green3, state.greens[2].title, state.greens[2].coast),
//                Item(R.drawable.green4, state.greens[3].title, state.greens[3].coast),
//            )
        ),
        Page(
            "03", "Выберите упаковку", "packs"
//            listOf(
//
////                Item(R.drawable.pack1, state.packs[0].title, state.packs[0].coast),
////                Item(R.drawable.pack2, state.packs[1].title, state.packs[1].coast),
////                Item(R.drawable.pack3, state.packs[2].title, state.packs[2].coast),
////                Item(R.drawable.pack4, state.packs[3].title, state.packs[3].coast),
//            )
        ),
        Page(
            "04", "Выберите открытку", "cards"
//            listOf(
////                Item(R.drawable.card1, state.cards[0].title, state.cards[0].coast),
////                Item(R.drawable.card2, state.cards[1].title, state.cards[1].coast),
////                Item(R.drawable.card3, state.cards[2].title, state.cards[2].coast),
////                Item(R.drawable.card4, state.cards[3].title, state.cards[3].coast),
//            )
        ),
        Page(
            "05", "Итого"
        ),

        )

    Column(modifier = Modifier.padding(horizontal = 25.dp)) {
        Logo(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 25.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(10.dp))
            Text(
                items[state.page].num,
                fontSize = 64.sp,
                fontFamily = fonts4,
                color = Color(0xFF732C2C),

                )
            Text(
                items[state.page].title,
                fontSize = 20.sp,
                fontFamily = fonts3,
                color = Color(0xFFA27474),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        if (state.page < 4) {
//            val item = items[page.value].items
            if (state.isLoading) {
                Text("Загрузка")
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    when (items[state.page].category) {
                        "flowers" -> {
                            items(state.flowers) { i ->
                                ItemCard(
                                    i.imageUrl, i.title, i.coast,
                                    action = { viewModel.onEvent(BouquetEvents.OnFlowerClick(i)) }
                                )
                            }
                        }
                        "greens" -> {
                            items(state.greens) { i ->
                                ItemCard(
                                    i.imageUrl, i.title, i.coast,
                                    action = { viewModel.onEvent(BouquetEvents.OnGreenClick(i))}
                                )
                            }
                        }


                        "packs" -> {
                            items(state.packs) { i ->
                                ItemCard(
                                    i.imageUrl, i.title, i.coast,
                                    action = {viewModel.onEvent(BouquetEvents.OnPackClick(i))}
                                )
                            }
                        }


                        "cards" -> {
                            items(state.cards) { i ->
                                ItemCard(
                                    i.imageUrl, i.title, i.coast,
                                    action = {viewModel.onEvent(BouquetEvents.OnCardClick(i))}
                                )
                            }
                        }
                        else -> {

                        }
                    }
                }
            }
        } else {
            LazyColumn {
                items(items.size) { i ->
                    val page = items[i]
                    Row() {
                        Text((i + 1).toString())
//                        Text(state.flower)
//                        Row() {
//                            Icon()
//                            Text("0")
//                            Icon()
//                        }
//                        Text(page.items)
                    }
                }
            }
        }
    }
}