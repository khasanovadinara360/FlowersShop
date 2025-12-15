package com.example.flowersshop.ui.pages.bouquet

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.flowersshop.R
import com.example.flowersshop.ui.components.ItemCard
import com.example.flowersshop.ui.components.Logo
import com.example.flowersshop.ui.theme.fonts3
import com.example.flowersshop.ui.theme.fonts4


data class Page(
    val num: String,
    val title: String,
    val category: String? = null
)


@Composable
fun BouquetScreen(
    navController: NavController,
    viewModel: BouquetViewModel = hiltViewModel(),
    cart: MutableState<Int>
) {
    val state = viewModel.state.value
    val items = listOf(
        Page("01", "Выберите цветок", "flowers"),
        Page("02", "Выберите зелень", "greens"),
        Page("03", "Выберите упаковку", "packs"),
        Page("04", "Выберите открытку", "cards"),
        Page("05", "Итого"),
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
                                    action = { viewModel.onEvent(BouquetEvents.OnGreenClick(i)) }
                                )
                            }
                        }


                        "packs" -> {
                            items(state.packs) { i ->
                                ItemCard(
                                    i.imageUrl, i.title, i.coast,
                                    action = { viewModel.onEvent(BouquetEvents.OnPackClick(i)) }
                                )
                            }
                        }


                        "cards" -> {
                            items(state.cards) { i ->
                                ItemCard(
                                    i.imageUrl, i.title, i.coast,
                                    action = { viewModel.onEvent(BouquetEvents.OnCardClick(i)) }
                                )
                            }
                        }

                        else -> {

                        }
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                val itemsBouquet = state.items
                items(itemsBouquet.size) { i ->
                    Log.e("TAG", "BouquetScreen: " + itemsBouquet[i].title, )
                    val isGreen = itemsBouquet[i].title.trim() == "Без зелени"
                    //remember { mutableStateOf(false) }

                    Box(Modifier.height(IntrinsicSize.Max)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopStart),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Text(
                                (i + 1).toString(),
                                fontSize = 16.sp,
                                fontFamily = fonts4,
                                color = Color(0xFF2B2B2B),
                                modifier = Modifier.padding(end = 16.dp)
                            )
                            Text(
                                itemsBouquet[i].title, modifier = Modifier.weight(1f),
                                fontSize = 16.sp,
                                fontFamily = fonts4,
                                color = Color(0xFF2B2B2B)
                            )
                            if (!isGreen) {
                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(18.5.dp))
                                        .background(Color(0xFFD9D9D9)),
                                    verticalAlignment = Alignment.CenterVertically
                                )
                                {
                                    Text(
                                        "-", modifier = Modifier
                                            .clickable {
                                                viewModel.onEvent(BouquetEvents.OnDelItemClick(state.items[i]))
                                            }
                                            .padding(start = 14.dp),
                                        fontSize = 16.sp,
                                        fontFamily = fonts4,
                                        color = Color(0xFF6A4B4B)
                                    )
                                    Text(
                                        when (i) {
                                            0 -> {
                                                state.flowersCount.toString()
                                            }

                                            1 -> {
                                                state.greensCount.toString()
                                            }

                                            2 -> {
                                                state.packsCount.toString()
                                            }

                                            3 -> {
                                                state.cardsCount.toString()
                                            }

                                            else -> {
                                                ""
                                            }
                                        },
                                        fontSize = 15.sp,
                                        fontFamily = fonts4,
                                        color = Color(0xFF2B2B2B),
                                        modifier = Modifier.padding(10.dp)
                                    )
                                    Text(
                                        "+", modifier = Modifier
                                            .clickable {
                                                viewModel.onEvent(BouquetEvents.OnAddItemClick(state.items[i]))
                                            }
                                            .padding(end = 14.dp),
                                        fontSize = 16.sp,
                                        fontFamily = fonts4,
                                        color = Color(0xFF6A4B4B)
                                    )
                                }
                                Spacer(Modifier.width(60.dp))
                            }
                        }
                        if (!isGreen) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            )
                            {
                                Spacer(Modifier.weight(1f))
                                Text(
                                    when (i) {
                                        0 -> {
                                            (itemsBouquet[i].coast * state.flowersCount).toString()
                                        }

                                        1 -> {
                                            (itemsBouquet[i].coast * state.greensCount).toString()
                                        }

                                        2 -> {
                                            (itemsBouquet[i].coast * state.packsCount).toString()
                                        }

                                        3 -> {
                                            (itemsBouquet[i].coast * state.cardsCount).toString()
                                        }

                                        else -> {
                                            ""
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(start = 16.dp)
                                )
                            }
                        }
                    }

                }
            }


            Text(
                "Результат составления букета",
                fontFamily = fonts3,
                fontSize = 15.sp,
                color = Color(0xFF2b2b2b),
                modifier = Modifier.align(
                    Alignment.CenterHorizontally
                )
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.resBouquetUrl)
                    .crossfade(true)
                    .build(),
                "bouquet",
                modifier = Modifier
                    .padding(horizontal = 55.dp, vertical = 10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(42.dp)),
                contentScale = ContentScale.FillWidth
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.weight(1f))
                Text("Итог:", fontSize = 16.sp, color = Color(0xFF2F1A1A), fontFamily = fonts3)
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 6.dp)
                        .clip(RoundedCornerShape(19.dp))
                        .background(Color(0xFF732C2C))
                        .width(59.dp)
                        .height(28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        state.coast.toString(),
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
                Image(
                    painter = painterResource(R.drawable.cart),
                    "cart",
                    modifier = Modifier
                        .height(29.dp)
                        .clickable { cart.value += 1 },
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    }


}
