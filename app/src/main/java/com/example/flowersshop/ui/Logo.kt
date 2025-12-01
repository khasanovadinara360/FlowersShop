package com.example.flowersshop.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.flowersshop.ui.theme.fonts
import com.example.flowersshop.ui.theme.fonts2

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "DaliAnge",
            fontFamily = fonts,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            color = Color.Black
        )
        Text(
            "Flowers",
            fontFamily = fonts2,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}