package com.example.flowersshop.ui.components

import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.flowersshop.R
import com.example.flowersshop.ui.theme.fonts3
import okhttp3.OkHttpClient
import okhttp3.Protocol
import java.util.concurrent.TimeUnit

@Composable
fun BouquetCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    desc: String,
    coast: Long,
    cartCount: MutableState<Int>
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .okHttpClient {
            OkHttpClient.Builder()
                .protocols(listOf(Protocol.HTTP_1_1))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }
        .build()

    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.error),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp)),
            onError = { error ->
                Log.e(
                    "AsyncImage",
                    "Ошибка загрузки картинки. Url $imageUrl",
                    error.result.throwable
                )

            },
                    imageLoader = imageLoader
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
                color = Color(0xFF0A1F33),
                maxLines = 3,
            )

            Spacer(Modifier.height(15.dp))

            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .background(Color(0xFF532A2A))
                    .clickable { cartCount.value += 1 }
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