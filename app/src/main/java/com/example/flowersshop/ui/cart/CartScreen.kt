package com.example.flowersshop.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.flowersshop.ui.Route
import com.example.flowersshop.ui.components.Logo
import com.example.flowersshop.ui.components.MyDialog
import com.example.flowersshop.ui.order.OrderEvents
import com.example.flowersshop.ui.theme.fonts3
import com.example.flowersshop.ui.theme.fonts4
import kotlinx.coroutines.launch

@Composable
fun CartScreen(navController: NavController, viewModel: CartViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Route.Order.route)

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
    ) {
        Logo(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 25.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {

            Text("Корзина", fontFamily = fonts3, fontSize = 30.sp, color = Color(0xFF2B2B2B))
            Spacer(Modifier.weight(1f))
            if (!state.isLoading) {
                Text(
                    "Очистить корзину",
                    fontFamily = fonts3,
                    fontSize = 15.sp,
                    color = Color(0x502B2B2B),
                    modifier = Modifier.clickable {
                        viewModel.onEvent(CartEvents.ClearClick)
                    })
            }
        }
        if (state.isLoading) {
            Text("Загрузка", color = Color.Black)
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp),

                )
            {
                val cart = state.products
                items(cart.size) { i ->
                    val item = cart[i]
                    when (item) {
                        is CartItem.CartBouquet -> {
                            Box(Modifier.height(IntrinsicSize.Max))
                            {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.TopStart),
                                    verticalAlignment = Alignment.CenterVertically
                                )
                                {
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(30.dp)
                                            .background(Color(0xFFD9D9D9))
                                    ) {
                                        Text(
                                            (i + 1).toString(),
                                            fontSize = 15.sp,
                                            fontFamily = fonts4,
                                            color = Color(0xFF2B2B2B),
                                            modifier = Modifier
                                                .align(Alignment.Center)

                                        )
                                    }
                                    Spacer(Modifier.width(16.dp))
                                    Text(
                                        item.title, modifier = Modifier.weight(1f),
                                        fontSize = 16.sp,
                                        fontFamily = fonts3,
                                        color = Color(0xFF2B2B2B)
                                    )
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
                                                    viewModel.onEvent(CartEvents.DelClick(i))
                                                }
                                                .padding(start = 14.dp),
                                            fontSize = 16.sp,
                                            fontFamily = fonts4,
                                            color = Color(0xFF6A4B4B)
                                        )
                                        Text(
                                            item.volume.toString(),
                                            fontSize = 15.sp,
                                            fontFamily = fonts4,
                                            color = Color(0xFF2B2B2B),
                                            modifier = Modifier.padding(10.dp)
                                        )
                                        Text(
                                            "+", modifier = Modifier
                                                .clickable {
                                                    viewModel.onEvent(CartEvents.AddClick(i))
                                                }
                                                .padding(end = 14.dp),
                                            fontSize = 16.sp,
                                            fontFamily = fonts4,
                                            color = Color(0xFF6A4B4B)
                                        )
                                    }
                                    Spacer(Modifier.width(50.dp))
                                }
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                )
                                {
                                    Spacer(Modifier.weight(1f))
                                    Text(
                                        item.coast.toString(),
                                        modifier = Modifier
                                            .padding(start = 16.dp),

                                        color = Color(0xFF2B2B2B),
                                        fontFamily = fonts4,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                        }

                        is CartItem.CustomBouquet -> {

                            Column {

                                Box(Modifier.height(IntrinsicSize.Max))
                                {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.TopStart),
                                        verticalAlignment = Alignment.CenterVertically
                                    )
                                    {
                                        Box(
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .size(30.dp)
                                                .background(Color(0xFFD9D9D9))
                                        ) {
                                            Text(
                                                (i + 1).toString(),
                                                fontSize = 15.sp,
                                                fontFamily = fonts4,
                                                color = Color(0xFF2B2B2B),
                                                modifier = Modifier
                                                    .align(Alignment.Center)

                                            )
                                        }
                                        Spacer(Modifier.width(16.dp))
                                        Text(
                                            item.title, modifier = Modifier.weight(1f),
                                            fontSize = 16.sp,
                                            fontFamily = fonts3,
                                            color = Color(0xFF2B2B2B)
                                        )
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
                                                        viewModel.onEvent(CartEvents.DelClick(i))
                                                    }
                                                    .padding(start = 14.dp),
                                                fontSize = 16.sp,
                                                fontFamily = fonts4,
                                                color = Color(0xFF6A4B4B)
                                            )
                                            Text(
                                                item.volume.toString(),
                                                fontSize = 15.sp,
                                                fontFamily = fonts4,
                                                color = Color(0xFF2B2B2B),
                                                modifier = Modifier.padding(10.dp)
                                            )
                                            Text(
                                                "+", modifier = Modifier
                                                    .clickable {
                                                        viewModel.onEvent(CartEvents.AddClick(i))
                                                    }
                                                    .padding(end = 14.dp),
                                                fontSize = 16.sp,
                                                fontFamily = fonts4,
                                                color = Color(0xFF6A4B4B)
                                            )
                                        }
                                        Spacer(Modifier.width(50.dp))
                                    }
                                    Row(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalAlignment = Alignment.CenterVertically
                                    )
                                    {
                                        Spacer(Modifier.weight(1f))
                                        Text(
                                            item.coast.toString(),
                                            modifier = Modifier
                                                .padding(start = 16.dp),
                                            color = Color(0xFF2B2B2B),
                                            fontFamily = fonts4,
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                                Column {
                                    item.components.forEach { comp ->
                                        Text(
                                            comp.title,
                                            fontSize = 12.sp,
                                            fontFamily = fonts3,
                                            color = Color(0xFF2B2B2B),
                                            modifier = Modifier.padding(start = 50.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier

                    .align(Alignment.End)
                    .padding(top = 10.dp)
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                val coroutineScope = rememberCoroutineScope()
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(19.dp))
                        .background(Color(0xFF732C2C))
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            viewModel.onEvent(CartEvents.OrderClick)
//                            val orderBackStackEntry = navController.getBackStackEntry(Route.Order.route)
//                            orderBackStackEntry.savedStateHandle["products"] = viewModel.state.value.products
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Оформить заказ",
                        fontSize = 15.sp,
                        fontFamily = fonts3,
                        color = Color.White
                    )
                }
                Spacer(Modifier.width(10.dp))
                Text("Итог:", fontSize = 16.sp, color = Color(0xFF2F1A1A), fontFamily = fonts3)
                Box(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clip(RoundedCornerShape(19.dp))
                        .background(Color(0xFF732C2C))
                        .width(59.dp)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        state.totalCoast.toString(),
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = fonts3
                    )
                }
            }
        }
        MyDialog("Ошибка", state.errorMessage, state.isError) {
            viewModel.onEvent(CartEvents.DismissClick)
        }
    }
}