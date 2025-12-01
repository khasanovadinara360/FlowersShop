package com.example.flowersshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flowersshop.R
import com.example.flowersshop.ui.Route

data class Btn(
    val icon: Int,
    val navigate: String,

    )

val buttons = listOf(
    Btn(R.drawable.home, Route.Main.route),
    Btn(R.drawable.cart, Route.Cart.route),
    Btn(R.drawable.profile, Route.Profile.route)
)

@Composable
fun BottomNav(navController: NavController, modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        repeat(buttons.size) { i ->
            val t = buttons[i]
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = { navController.navigate(t.navigate) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(
                        painter = painterResource(t.icon),
                        contentDescription = t.navigate,
                        tint = Color(0xFF000000)
                    )
                }
                Spacer(
                    Modifier
                        .height(3.dp)
                        .background(Color.Green)
                )
                Text(t.navigate)
            }
        }
    }
}
