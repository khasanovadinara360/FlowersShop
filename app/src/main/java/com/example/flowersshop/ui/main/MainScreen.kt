package com.example.flowersshop.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flowersshop.R
import com.example.flowersshop.data.GetBouquets

import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.example.flowersshop.data.Bouquet

@Composable
fun MainScreen(navController: NavController) {
    val getBouquets = GetBouquets()
//    val : List<Bouquet>
    var bouquets by remember { mutableStateOf<List<Bouquet>>(emptyList()) }
    LaunchedEffect(Unit) {
        bouquets = getBouquets.execute()
    }


    Scaffold(
        containerColor = Color.Black
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color.Black)
        ) {
            // Верхние вкладки
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {

                    CategoryChip("Сборные Букеты", true)
                }
                item {

                    CategoryChip("Моно Букеты")
                }
                item {

                    CategoryChip("Букеты в коробке")
                }
            }
            if (!bouquets.isEmpty()) {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                )
                {
                    items(bouquets.size) { item ->
                        FlowerCard(
                            title = bouquets[item].title,
                            tag = bouquets[item].tag,
                            discount = if (bouquets[item].discount != "0") {
                                bouquets[item].discount
                            } else {
                                ""
                            },
                            tagColor = Color(0xFFB84DFF),
                            imageRes = R.drawable.racoon,
                            url = bouquets[item].imageUrl
                        )
                    }

//                item {
//                    FlowerCard(
//                        title = "Букет «Мечта»",
//                        tag = "НОВИНКА",
//                        tagColor = Color(0xFFB84DFF),
//                        imageRes = R.drawable.racoon
//                    )
//                }
//                item {
//                    FlowerCard(
//                        title = "Ароматный букет",
//                        tag = "АКЦИЯ",
//                        discount = "-13%",
//                        tagColor = Color(0xFFFF4D4D),
//                        imageRes = R.drawable.racoon
//                    )
//                }
                }
            }
            else {Text("Загрузка...")}

            // Список букетов
        }
    }
}

@Composable
fun CategoryChip(title: String, selected: Boolean = false) {


    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) Color.DarkGray else Color(0xFF2C2C2C))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun FlowerCard(
    title: String,
    tag: String,
    tagColor: Color,
    imageRes: Int,
    discount: String? = null,
    url: String
) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1E1E1E))
    ) {
        Column {
            Box {
                AsyncImage(
                    model = url,
                    contentDescription = "bouquet",
                    placeholder = painterResource(R.drawable.racoon), // при загрузке
                    error = painterResource(R.drawable.racoon),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = ContentScale.Crop
                )

//                Image(
//                    painter = painterResource(imageRes),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(180.dp)
//                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
//                    contentScale = ContentScale.Crop
//                )
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TagChip(tag, tagColor)
                    if (discount != null) TagChip(discount, Color(0xFF7B68EE))
                }
            }
            Text(
                text = title,
                modifier = Modifier.padding(8.dp),
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun TagChip(text: String, color: Color) {
    Box(
        modifier = Modifier
            .background(color, RoundedCornerShape(6.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}
//
//@Composable
//fun BottomNavigationBar() {
//    BottomNavigation(
//        backgroundColor = Color.Black,
//        contentColor = Color.White
//    ) {
//        BottomNavigationItem(
//            icon = { Icon(Icons.Default.Favorite, null) },
//            label = { Text("Каталог") },
//            selected = true,
//            onClick = {}
//        )
//        BottomNavigationItem(
//            icon = { Icon(Icons.Default.Person, null) },
//            label = { Text("Профиль") },
//            selected = false,
//            onClick = {}
//        )
//        BottomNavigationItem(
//            icon = { Icon(Icons.Default.ShoppingCart, null) },
//            label = { Text("3880 ₽") },
//            selected = false,
//            onClick = {}
//        )
//    }
//}
