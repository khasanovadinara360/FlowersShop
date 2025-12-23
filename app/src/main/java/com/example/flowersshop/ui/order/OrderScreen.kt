package com.example.flowersshop.ui.order

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flowersshop.ui.Route
import com.example.flowersshop.ui.cart.CartItem
import com.example.flowersshop.ui.components.Logo
import com.example.flowersshop.ui.components.MyDialog
import com.example.flowersshop.ui.order.OrderEvents
import com.example.flowersshop.ui.theme.brown
import com.example.flowersshop.ui.theme.fonts3
import com.example.flowersshop.ui.theme.fonts4
import com.example.flowersshop.ui.theme.gray

@Composable
fun OrderScreen(navController: NavController, viewModel: OrderViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val context = LocalContext.current
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            Toast.makeText(context, "Заказ успешно создан!", Toast.LENGTH_SHORT).show()
            navController.navigate(Route.Main.route)
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
        Text(
            "Оформление заказа",
            fontFamily = fonts3,
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 15.dp),
            color = Color.Black
        )
        val scrollState = rememberScrollState()


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        )
        {
            item {
                Column()
                {
                    Text(
                        "Информация о доставке",
                        fontFamily = fonts3,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 10.dp),
                        color = brown
                    )
                    Row()
                    {
                        Text(
                            "Способ получения",
                            fontFamily = fonts3,
                            fontSize = 16.sp,
                            color = Color(0xFF2B2B2B),
                            modifier = Modifier.weight(1f)
                        )
                        Row(modifier = Modifier.weight(1f)) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 9.dp)
                            ) {
                                ButtonItem(
                                    color = if (state.delivery == "Доставка") brown
                                    else gray,
                                    text = "Доставка",
                                    textColor = if (state.delivery == "Доставка") Color.White
                                    else Color.Black,
                                    onClick = {
                                        viewModel.onEvent(OrderEvents.OnDeliveryClick("Доставка"))
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                ButtonItem(
                                    color = if (state.delivery == "Доставка") brown
                                    else gray,
                                    text = "+ 200",
                                    textColor = if (state.delivery == "Доставка") Color.White
                                    else Color.Black,
                                    onClick = {
                                        viewModel.onEvent(OrderEvents.OnDeliveryClick("Доставка"))

                                    },
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .fillMaxWidth()
                                )
                            }
                            Column(modifier = Modifier.weight(1f)) {
                                ButtonItem(
                                    color = if (state.delivery == "Самовывоз") brown
                                    else gray,
                                    text = "Самовывоз",
                                    textColor = if (state.delivery == "Самовывоз") Color.White
                                    else Color.Black,
                                    onClick = {
                                        viewModel.onEvent(OrderEvents.OnDeliveryClick("Самовывоз"))

                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                                ButtonItem(
                                    color = if (state.delivery == "Самовывоз") brown
                                    else gray,
                                    text = "+ 0",
                                    textColor = if (state.delivery == "Самовывоз") Color.White
                                    else Color.Black,
                                    onClick = {
                                        viewModel.onEvent(OrderEvents.OnDeliveryClick("Самовывоз"))

                                    },
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text(
                            "Способ оплаты",
                            fontFamily = fonts3,
                            fontSize = 16.sp,
                            color = Color(0xFF2B2B2B),
                            modifier = Modifier.weight(1f)
                        )

                        Row(modifier = Modifier.weight(1f)) {
                            ButtonItem(
                                color = if (state.payment == "При получении") brown
                                else gray,
                                text = "При получении",
                                textColor = if (state.payment == "При получении") Color.White
                                else Color.Black,
                                onClick = {
                                    viewModel.onEvent(OrderEvents.OnPaymentClick("При получении"))

                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.5f)
                            )
                            Spacer(Modifier.width(8.dp))

                            ButtonItem(
                                color = if (state.payment == "По факту") brown
                                else gray,
                                text = "По факту",
                                textColor = if (state.payment == "По факту") Color.White
                                else Color.Black,
                                onClick = {
                                    viewModel.onEvent(OrderEvents.OnPaymentClick("По факту"))

                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.5f)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text(
                            "Адрес доставки",
                            fontFamily = fonts3,
                            fontSize = 16.sp,
                            color = Color(0xFF2B2B2B),
                            modifier = Modifier.weight(1f)
                        )
                        OrderTextField(
                            state.address,
                            {
                                viewModel.onEvent(OrderEvents.OnAddressChange(it))
                            },
                            modifier = Modifier.weight(1f)
                        )

                    }

                    Text(
                        "Контактные данные",
                        fontFamily = fonts3,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 25.dp),
                        color = brown
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text(
                            "Имя",
                            fontFamily = fonts3,
                            fontSize = 16.sp,
                            color = Color(0xFF2B2B2B),
                            modifier = Modifier.weight(0.5f)
                        )
                        OrderTextField(
                            state.name,
                            {
                                viewModel.onEvent(OrderEvents.OnNameChange(it))
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text(
                            "Телефон",
                            fontFamily = fonts3,
                            fontSize = 16.sp,
                            color = Color(0xFF2B2B2B),
                            modifier = Modifier.weight(0.5f)
                        )
                        OrderTextField(
                            state.phone,
                            {
                                viewModel.onEvent(OrderEvents.OnPhoneChange(it))
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text(
                            "Комментарий",
                            fontFamily = fonts3,
                            fontSize = 16.sp,
                            color = Color(0xFF2B2B2B),
                            modifier = Modifier.weight(0.5f)
                        )
                        OrderTextField(
                            state.comment,
                            {
                                viewModel.onEvent(OrderEvents.OnCommentChange(it))
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Text(
                        "Товары в заказе",
                        fontFamily = fonts3,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 20.dp),
                        color = brown
                    )
                }
            }
            if (state.isLoading) {
                item() {
                    Text("Загрузка", color = Color.Black)
                }
            } else {
                items(state.products.size)
                { i ->
                    val product = state.products[i]
                    when (product) {
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
                                    Text(
                                        (i + 1).toString(),
                                        fontSize = 16.sp,
                                        fontFamily = fonts4,
                                        color = Color(0xFF2B2B2B),
                                        modifier = Modifier.padding(end = 16.dp)
                                    )
                                    Text(
                                        product.title, modifier = Modifier.weight(1f),
                                        fontSize = 16.sp,
                                        fontFamily = fonts4,
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
                                                    viewModel.onEvent(OrderEvents.DelClick(i))
                                                }
                                                .padding(start = 14.dp),
                                            fontSize = 16.sp,
                                            fontFamily = fonts4,
                                            color = Color(0xFF6A4B4B)
                                        )
                                        Text(
                                            product.volume.toString(),
                                            fontSize = 15.sp,
                                            fontFamily = fonts4,
                                            color = Color(0xFF2B2B2B),
                                            modifier = Modifier.padding(10.dp)
                                        )
                                        Text(
                                            "+", modifier = Modifier
                                                .clickable {
                                                    viewModel.onEvent(OrderEvents.AddClick(i))
                                                }
                                                .padding(end = 14.dp),
                                            fontSize = 16.sp,
                                            fontFamily = fonts4,
                                            color = Color(0xFF6A4B4B)
                                        )
                                    }
                                    Spacer(Modifier.width(60.dp))

                                }
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                )
                                {
                                    Spacer(Modifier.weight(1f))
                                    Text(
                                        (product.coast * product.volume).toString(),
                                        modifier = Modifier
                                            .padding(start = 16.dp),
                                        fontFamily = fonts4,
                                        fontSize = 15.sp, color = Color.Black
                                    )
                                }

                            }

                        }

                        is CartItem.CustomBouquet -> {
                            Box(Modifier.height(IntrinsicSize.Max))
                            {
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
                                        product.title, modifier = Modifier.weight(1f),
                                        fontSize = 16.sp,
                                        fontFamily = fonts4,
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
                                                    viewModel.onEvent(OrderEvents.DelClick(i))
                                                }
                                                .padding(start = 14.dp),
                                            fontSize = 16.sp,
                                            fontFamily = fonts4,
                                            color = Color(0xFF6A4B4B)
                                        )
                                        Text(
                                            product.volume.toString(),
                                            fontSize = 15.sp,
                                            fontFamily = fonts4,
                                            color = Color(0xFF2B2B2B),
                                            modifier = Modifier.padding(10.dp)
                                        )
                                        Text(
                                            "+", modifier = Modifier
                                                .clickable {
                                                    viewModel.onEvent(OrderEvents.AddClick(i))
                                                }
                                                .padding(end = 14.dp),
                                            fontSize = 16.sp,
                                            fontFamily = fonts4,
                                            color = Color(0xFF6A4B4B)
                                        )
                                    }
                                    Spacer(Modifier.width(60.dp))

                                }
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                )
                                {
                                    Spacer(Modifier.weight(1f))
                                    Text(
                                        (product.coast * product.volume).toString(),
                                        modifier = Modifier
                                            .padding(start = 16.dp),
                                        fontFamily = fonts4,
                                        fontSize = 15.sp, color = Color.Black
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
                .padding(vertical = 10.dp)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(19.dp))
                    .background(Color(0xFF732C2C))
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        viewModel.onEvent(OrderEvents.OnOrderClick(1))

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
        MyDialog("Ошибка", state.errorMessage, state.isError) {
            viewModel.onEvent(OrderEvents.OnDismissClick)
        }
    }
}

@Composable
fun ButtonItem(
    color: Color,
    text: String,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(19.dp))
            .background(color)
            .clickable {
                onClick()
            }
            .height(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontFamily = fonts3, fontSize = 10.sp, color = textColor)
    }
}

@Composable
fun OrderTextField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier/*
                    .height(30.dp)*/, colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = gray,
            focusedContainerColor = gray,
        ),
        shape = RoundedCornerShape(19.dp),
        textStyle = TextStyle(
            fontFamily = fonts4,
            fontSize = 12.sp,
            color = Color.Black
        )
    )
}