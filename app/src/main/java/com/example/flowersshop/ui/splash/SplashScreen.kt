package com.example.flowersshop.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flowersshop.R
import com.example.flowersshop.ui.Route
import com.example.flowersshop.ui.theme.fonts
import com.example.flowersshop.ui.theme.fonts2

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    LaunchedEffect(state.isTimeOut) {
        if (state.isTimeOut) {
            navController.navigate(Route.Onb.route)
        }

    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.splash),
            "splash",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(IntrinsicSize.Max)
                ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bouquet),
                "flower",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp).clickable{
                        navController.navigate(Route.Onb.route)
                    },
                contentScale = ContentScale.FillWidth
            )
            Text(
                "DaliAnge",
                fontFamily = fonts,
                fontWeight = FontWeight.Normal,
                fontSize = 32.sp,
                color = Color.Black
            )
            Text("Flowers",
                fontFamily = fonts2,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Color.Black)
        }
    }
}