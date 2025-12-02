package com.example.flowersshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChip(title: String, selected: Boolean = false, category: MutableState<String>) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) Color(0xFF655F5F) else Color(0xFFAFAFAF))
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { category.value = title }
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
