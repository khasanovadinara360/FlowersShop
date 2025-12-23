package com.example.flowersshop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flowersshop.R
import com.example.flowersshop.ui.Route
import com.example.flowersshop.domain.repository.LocalCartRepository
import kotlinx.coroutines.flow.map

data class Btn(
    val icon: Int,
    val navigate: String,
)

val buttons = listOf(
    Btn(R.drawable.home, Route.Main.route),
    Btn(R.drawable.bouquet, Route.Bouquet.route),
    Btn(R.drawable.favourites, Route.Favourites.route),
    Btn(R.drawable.cart, Route.Cart.route),
)

@Composable
fun BottomNav(
    navController: NavController, modifier: Modifier,
    cartRepository: LocalCartRepository
) {
    LaunchedEffect(Unit) {
        cartRepository.loadCart()
    }
    val count by cartRepository.items
    .map { it.values.sum() }
    .collectAsState(initial = 0)
//    val count by viewModel.count.collectAsState()
    val currentPage = remember { mutableStateOf("main") }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(buttons.size) { i ->
            val t = buttons[i]
            Box(
                modifier =
                    if (t.navigate == currentPage.value) {
                        Modifier
                            .size(50.dp)
                            .shadow(
                                elevation = 10.dp,
                                shape = RoundedCornerShape(1.dp),
                                clip = false,
                                ambientColor = Color.Black
                            )
                            .background(Color.White, RoundedCornerShape(1.dp))
                            .clickable {
                                currentPage.value = t.navigate
                                navController.navigate(t.navigate) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
//                                navController.navigate(t.navigate)
                            }
                    } else {
                        Modifier
                            .size(50.dp)
                            .background(Color.White)
                            .clickable {
                                currentPage.value = t.navigate
                                navController.navigate(t.navigate)
                            }
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(t.icon),
                    contentDescription = t.navigate,
                    modifier = Modifier.size(50.dp)
                )
                if (count > 0 && t.navigate == Route.Cart.route) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color.White, shape = CircleShape)
                            .align(Alignment.TopEnd), contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(Color.Red, shape = CircleShape)
                                .align(Alignment.Center)
                        ) {
                            Text(
                                text = count.toString(),
                                color = Color.White,
                                fontSize = 12.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}
