package com.example.flowersshop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flowersshop.ui.theme.fonts3

@Composable
fun BouquetCard(
    modifier: Modifier = Modifier,
    image: Int,
    title: String,
    desc: String,
    coast: Int,
    cartCount: MutableState<Int>
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp))
        )

        Spacer(Modifier.height(11.dp))

        Column(Modifier.padding(horizontal = 8.dp)) {

            Text(
                text = title,
                fontSize = 12.sp,
                fontFamily = fonts3,
                color = Color(0xFF0A1F33)
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = desc,
                fontSize = 8.sp,
                fontFamily = fonts3,
                color = Color(0xFF0A1F33)
            )

            Spacer(Modifier.height(15.dp))

            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .background(Color(0xFF532A2A))
                    .clickable {cartCount.value += 1}
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = coast.toString(),
                    fontSize = 8.sp,
                    fontFamily = fonts3,
                    color = Color.White
                )
            }
        }
    }
}