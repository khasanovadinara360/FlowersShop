package com.example.flowersshop.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.flowersshop.R

// Set of Material typography styles to start with


val fonts = FontFamily(
    Font(R.font.wmd_r, FontWeight.Normal),
)
val fonts2 = FontFamily(
    Font(R.font.ws_r, FontWeight.Normal),
)
val fonts3 = FontFamily(
    Font(R.font.pd_r, FontWeight.Normal),
)
val fonts4 = FontFamily(
    Font(R.font.ct_r, FontWeight.Normal),
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,

    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)