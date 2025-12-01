package com.example.flowersshop.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flowersshop.R
import com.example.flowersshop.ui.Logo
import com.example.flowersshop.ui.Route
import com.example.flowersshop.ui.theme.fonts

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.login), "login",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(35.dp)
        ) {

            Logo(modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(
                "Добро",
                modifier = Modifier.padding(top = 20.dp),
                fontSize = 48.sp,
                fontFamily = fonts,
                color = Color(0xFF4B2F12)
            )
            Text(
                "пожаловать", /*modifier = Modifier.padding(top = 20.dp),*/
                fontSize = 32.sp,
                fontFamily = fonts,
                color = Color(0xFF4B2F12)
            )
            Text(
                "Войдите, чтобы продолжить",
                modifier = Modifier.padding(top = 8.dp, bottom = 20.dp),
                fontSize = 16.sp,
                fontFamily = fonts,
                color = Color(0xFF362A1E)
            )
            TextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(LoginEvents.OnEmailChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(60.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0x40D9D9D9),
                    unfocusedContainerColor = Color(0x40D9D9D9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
            TextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(LoginEvents.OnPasswordChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(60.dp))
                    .padding(top = 8.dp, bottom = 17.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0x40D9D9D9),
                    unfocusedContainerColor = Color(0x40D9D9D9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
            Text(
                "Забыли пароль?", fontSize = 11.sp,
                fontFamily = fonts,
                color = Color(0xFFF7F4F1)
            )
            Button(
                onClick = { navController.navigate(Route.Main.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(9.dp))
                    .height(68.dp)
                    .padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B2B2B)
                )
            ) {
                Text(
                    "Войти", fontSize = 16.sp,
                    fontFamily = fonts,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }

}