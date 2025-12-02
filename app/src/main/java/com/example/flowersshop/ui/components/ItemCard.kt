package com.example.flowersshop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flowersshop.R
import com.example.flowersshop.ui.pages.bouquet.BouquetViewModel
import com.example.flowersshop.ui.theme.fonts3

@Composable
fun ItemCard(image: Int, title: String, coast: Long, action: Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(26.dp))
            .background(Color(0xFFD9D9D9))
            .clickable{
                action
            }
            .padding( 5.dp)
    ) {
        Image(
            painter = painterResource(image),
            "flower",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(21.dp))
        )
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(title, fontFamily = fonts3, fontSize = 14.sp, color = Color(0xFF2B2B2B))
            Text("₱$coast", fontFamily = fonts3, fontSize = 15.sp, color = Color(0xFF2B2B2B))
        }
    }
}

@Preview
@Composable
fun ItemCardPrev() {
    //ItemCard(R.drawable.flower1, "Раникулюнкус", 200)
}