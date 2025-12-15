package com.example.flowersshop.ui.bouquet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.flowersshop.R
import com.example.flowersshop.ui.Route
import com.example.flowersshop.ui.components.Logo
import com.example.flowersshop.ui.theme.fonts3
import com.example.flowersshop.ui.theme.fonts4

@Composable
fun BouquetCardScreen(
    navController: NavController,
    viewModel: BouquetCardViewModel = hiltViewModel(),
    cartCount: MutableState<Int>
) {
    val state = viewModel.state.value
    val isFavourite = remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(horizontal = 25.dp)) {
        Logo(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 25.dp)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.back),
            "back",
            tint = Color(0xFF7C5454),
            modifier = Modifier
                .clickable {
                    navController.navigate(Route.Main.route)
                }
                .padding(bottom = 10.dp)
                .align(Alignment.Start)
        )
        if (state.isLoading) {
            Text("Загрузка")
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                )
                {
                    val bouquet = state.bouquet!!
                    Box() {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(bouquet.imageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            placeholder = painterResource(R.drawable.placeholder),
                            error = painterResource(R.drawable.error),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(26.dp)),
                        )

                        Image(
                            painter = if (state.isFavourite) {
                                painterResource(R.drawable.favourite_select)
                            } else {
                                painterResource(R.drawable.favourites)
                            },
                            null,
                            modifier = Modifier
                                .size(39.dp)
                                .padding(end = 14.dp, bottom = 14.dp)
                                .clickable {
                                    viewModel.onEvent(BouquetCardEvents.OnFavouriteClick(bouquet.id))
                                }
                                .align(Alignment.BottomEnd))
                    }
                    Spacer(Modifier.height(35.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            bouquet.title,
                            modifier = Modifier.weight(1f),
                            fontFamily = fonts3,
                            fontSize = 25.sp,
                            color = Color(0xFF2B2B2B)
                        )
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .width(80.dp)
                                .clip(RoundedCornerShape(19.dp))
                                .background(Color(0xFF532A2A))
                                .clickable { cartCount.value += 1 },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = bouquet.coast.toString(),
                                fontSize = 20.sp,
                                fontFamily = fonts4,
                                color = Color.White
                            )
                        }
                    }
                    Text(
                        bouquet.desc,
                        modifier = Modifier.padding(top = 15.dp, bottom = 26.dp),
                        fontFamily = fonts3,
                        fontSize = 15.sp,
                        color = Color(0xFF2B2B2B)
                    )
                    if (bouquet.width != null && bouquet.height != null) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 14.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(0xFFD9D9D9))

                        ) {
                            Text(
                                "Ширина",
                                modifier = Modifier
                                    .padding(start = 42.dp, top = 10.dp, bottom = 10.dp)
                                    .align(Alignment.CenterStart),
                                fontFamily = fonts4,
                                fontSize = 11.sp,
                                color = Color(0xFF2B2B2B)
                            )
                            Text(
                                bouquet.width.toString() + " см",
                                modifier = Modifier
                                    .padding(end = 42.dp)
                                    .align(Alignment.CenterEnd),
                                fontFamily = fonts4,
                                fontSize = 11.sp,
                                        color = Color(0xFF2B2B2B)
                            )
                        }
                        Spacer(Modifier.height(7.dp))
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 14.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(0xFFD9D9D9))

                        ) {
                            Text(
                                "Высота",
                                modifier = Modifier
                                    .padding(start = 42.dp, top = 10.dp, bottom = 10.dp)
                                    .align(Alignment.CenterStart),
                                fontFamily = fonts4,
                                fontSize = 11.sp,
                                color = Color(0xFF2B2B2B)

                            )
                            Text(
                                bouquet.height.toString() + " см",
                                modifier = Modifier
                                    .padding(end = 42.dp)
                                    .align(Alignment.CenterEnd),
                                fontFamily = fonts4,
                                fontSize = 11.sp,
                                color = Color(0xFF2B2B2B)

                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                {
                    val height = remember { mutableStateOf(0.dp) }
                    val density = LocalDensity.current
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.5.dp))
                            .background(Color(0xFFD9D9D9))
                            .onGloballyPositioned {

                                val tempHeight = it.size.height
                                height.value = with(density) { tempHeight.toDp() }

                            },
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text(
                            "-", modifier = Modifier
                                .clickable {
                                    viewModel.onEvent(BouquetCardEvents.OnDelClick)
                                    if (cartCount.value > 0) {
                                        cartCount.value -= 1
                                    }
                                }
                                .padding(start = 14.dp),
                            fontSize = 16.sp,
                            fontFamily = fonts4,
                            color = Color(0xFF6A4B4B)
                        )
                        Text(
                            state.count.toString(),
                            fontSize = 15.sp,
                            fontFamily = fonts4,
                            color = Color(0xFF2B2B2B),
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            "+", modifier = Modifier
                                .clickable {
                                    viewModel.onEvent(BouquetCardEvents.OnAddClick)
                                }
                                .padding(end = 14.dp),
                            fontSize = 16.sp,
                            fontFamily = fonts4,
                            color = Color(0xFF6A4B4B)
                        )
                    }
                    Spacer(Modifier.width(7.dp))
                    Box(
                        modifier = Modifier
                            .height(height.value)
                            .clip(RoundedCornerShape(19.dp))
                            .background(Color(0xFF6A4B4B))
                            .weight(1.56f)
                            .clickable { cartCount.value += 1 }
                    ) {
                        Text(
                            "В корзину",
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.White,
                            fontFamily = fonts4,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

