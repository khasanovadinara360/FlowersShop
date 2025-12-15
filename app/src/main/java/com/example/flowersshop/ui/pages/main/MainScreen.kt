package com.example.flowersshop.ui.pages.main

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flowersshop.R
import com.example.flowersshop.ui.Route
import com.example.flowersshop.ui.components.BouquetCard
import com.example.flowersshop.ui.components.CategoryChip
import com.example.flowersshop.ui.components.Logo

data class Item(
    @DrawableRes val image: Int,
    val title: String,
    val desc: String,
    val coast: Int
)

@Composable
fun MainScreen(
    navController: NavController,
    cartCount: MutableState<Int>,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LaunchedEffect(Unit) {
        viewModel.getData()
    }
    val cats = listOf(
        "Часто заказывают",
        "Новинки",
        "Сборные букеты",
        "Моно букеты",
        "Букеты PREMIUM",
        "Комбо",
        "Букеты-гиганты",
        "Композиции в корзине",
        "Композиции в коробке",
        "Композиции в сумочке",
        "Мягкие игрушки",
        "Воздушные шары",
    )
    val items = mapOf(
        "Часто заказывают" to listOf(
            Item(
                R.drawable.lilii,
                "Букет ароматных лилий",
                "5 королевских лилий в оформлении",
                3900
            ),
            Item(
                R.drawable.lilii,
                "Букет ароматных лилий",
                "5 королевских лилий в оформлении",
                3900
            ),
            Item(
                R.drawable.lilii,
                "Букет ароматных лилий",
                "5 королевских лилий в оформлении",
                3900
            )
        ),
        "Новинки" to listOf(
            Item(
                R.drawable.lilii,
                "Букет ароматных членов",
                "5 королевских членов в оформлении",
                3900
            ),
            Item(
                R.drawable.lilii,
                "Букет ароматных членов",
                "5 королевских членов в оформлении",
                3900
            ),
            Item(
                R.drawable.lilii,
                "Букет ароматных членов",
                "5 королевских членов в оформлении",
                3900
            )
        ),
        "Сборные букеты" to listOf(
            Item(
                R.drawable.lilii,
                "Букет ароматных лилий",
                "5 королевских лилий в оформлении",
                3900
            ),
            Item(
                R.drawable.lilii,
                "Букет ароматных лилий",
                "5 королевских лилий в оформлении",
                3900
            ),
            Item(
                R.drawable.lilii,
                "Букет ароматных лилий",
                "5 королевских лилий в оформлении",
                3900
            )
        ),
        "Моно букеты" to listOf(
            Item(
                R.drawable.lilii,
                "Букет ароматных лилий",
                "5 королевских лилий в оформлении",
                3900
            ),
            Item(
                R.drawable.lilii,
                "Букет ароматных лилий",
                "5 королевских лилий в оформлении",
                3900
            ),
            Item(
                R.drawable.lilii,
                "Букет ароматных лилий",
                "5 королевских лилий в оформлении",
                3900
            )
        ),
//        "Букеты PREMIUM" to listOf(""),
//        "Комбо" to listOf(""),
//        "Букеты-гиганты" to listOf(""),
//        "Композиции в корзине" to listOf(""),
//        "Композиции в коробке" to listOf(""),
//        "Композиции в сумочке" to listOf(""),
//        "Мягкие игрушки" to listOf(""),
//        "Воздушные шары" to listOf("")
    )
//    val category = remember { mutableStateOf("Часто заказывают") }
    Column(
        modifier = Modifier.padding(horizontal = 25.dp)
    ) {
        Logo(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 25.dp)
        )
        val cards = listOf(
            R.drawable.carousel1,
            R.drawable.carousel2,
        )
        val pagerState = rememberPagerState { cards.size }
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { card ->
            Image(
                painter = painterResource(cards[card]),
                null,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(cards.size) { i ->
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(9.dp)
                        .background(
                            if (pagerState.currentPage == i) Color(0xFF655F5F)
                            else Color(0xFFD9D9D9)
                        )
                )
            }
        }
//        Box(
//            modifier = Modifier
//                .clip(RoundedCornerShape(16.dp))
//                .fillMaxWidth()
//                .height(150.dp)
//                .background(Color(0xFFd9d9d9))
//        )
//        Spacer(Modifier.height(20.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.categories) { i ->
                CategoryChip(i, i == state.category, viewModel)
            }
        }
        Spacer(Modifier.height(15.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            val list = state.bouquets
//                if (items[category.value] != null) {
//                items[category.value]!!
//            } else {
//                items["Часто заказывают"]!!
//            }
            items(
                list.size
            ) { t ->
                val i = list[t]
                BouquetCard(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .weight(1f)
                        .background(Color(0xFFE4E3E1), shape = RoundedCornerShape(28.dp))
                        .padding(bottom = 15.dp)
                        .clickable { navController.navigate(Route.BouquetCard.createRoute(i.id)) },
                    i.imageUrl,
                    i.title,
                    i.desc,
                    i.coast,
                    cartCount,

                    )
            }
        }
    }
}

