package com.example.flowersshop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.flowersshop.R
import com.example.flowersshop.ui.theme.fonts3

@Composable
fun ItemCard(
    imageUrl: String,
    title: String,
    coast: Long,
    action: () -> Unit = {},
    iconFav: Boolean = false
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(26.dp))
            .background(Color(0xFFD9D9D9))
            .clickable {
                action()
            }
            .padding(5.dp)
    ) {
        if (imageUrl.isNotBlank()) {


            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "item",
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.login),
                modifier = Modifier
                    .heightIn(158.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(21.dp))
            )
        } else {
            Image(
                painter = painterResource(R.drawable.placeholder), null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .heightIn(158.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(21.dp))
            )
        }
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
    ItemCard("", "Раникулюнкус", 200L, {})
}