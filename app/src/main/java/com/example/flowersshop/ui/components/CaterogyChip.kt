package com.example.flowersshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flowersshop.ui.pages.main.MainEvents
import com.example.flowersshop.ui.pages.main.MainViewModel

@Composable
fun CategoryChip(title: String, selected: Boolean = false, viewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) Color(0xFF655F5F) else Color(0xFFAFAFAF))
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                viewModel.onEvent(MainEvents.OnCategoryChange(title))
            }
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
