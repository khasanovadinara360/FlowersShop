package com.example.flowersshop.ui.onb

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flowersshop.R
import com.example.flowersshop.ui.theme.fonts

data class Onb(
    @DrawableRes val image: Int,
    val num: Pair<Int, Color>,
    val title: Pair<String, Color>,
    val desc: Pair<String, Color>,
    val color: Color

)

@Composable
fun OnbScreen(navController: NavController) {
    val onbs = listOf(
        Onb(
            R.drawable.onb1,
            Pair(1, Color(0xFF2B2B2B)),
            Pair("Цветы, достойные вашего выбора", Color(0xFF0A2640)),
            Pair(
                "Открывайте утончённые букеты в вашем городе — легко и элегантно.", Color(
                    0xFF05281C
                )
            ),
            Color(0xFF0A2640)
        ),
        Onb(
            R.drawable.onb2,
            Pair(2, Color(0xFFFFFFFF)),
            Pair("Создавайте композиции сами", Color(0xFF440518)),
            Pair(
                "Выбирайте лучшие цветы и формируйте собственный букет — изысканный, уникальный, в вашем стиле.",
                Color(
                    0xFF043420
                )
            ),
            Color(0xFF440518)
        ),
        Onb(
            R.drawable.onb3,
            Pair(3, Color(0xFF000000)),
            Pair("Мечтайте в оттенках роскоши", Color(0xFF440518)),
            Pair(
                "Окружите себя цветами, которые вдохновляют и создают атмосферу совершенства.",
                Color(
                    0xFF043420
                )
            ),
            Color(0xFF440518)
        ),
    )
    val i = remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(onbs[i.value].image), "onb",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.fillMaxSize().padding (20.dp)
        ) {

            Text(
                "0" + onbs[i.value].num.first,
                fontFamily = fonts,
                fontSize = 128.sp,
                fontWeight = FontWeight.Normal,
                color = onbs[i.value].num.second
            )
            Spacer(Modifier.weight(1f))
            Text(
                onbs[i.value].title.first,
                fontFamily = fonts,
                fontSize = 41.sp,
                fontWeight = FontWeight.Normal,
                color = onbs[i.value].title.second
            )
            Text(
                onbs[i.value].desc.first,
                modifier = Modifier.padding(bottom = 35.dp),
                fontFamily = fonts,
                fontSize = 21.sp,
                fontWeight = FontWeight.Normal,
                color = onbs[i.value].desc.second
            )
//            Button(onClick = { i.value++ }, modifier = Modifier.align(Alignment.End), colors = ButtonDefaults.buttonColors(contentColor = Color.Transparent)) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.next),
                    "next",
                    tint = onbs[i.value].color,
                    modifier = Modifier.clickable{i.value += 1}.align(Alignment.End)
                )
//            }
        }
    }
}