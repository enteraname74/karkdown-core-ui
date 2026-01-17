package com.github.enteraname74.karkdowncoreui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class KarkdownCoreUiTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val body: TextStyle,
    val small: TextStyle,
) {
    companion object {
        val default = KarkdownCoreUiTypography(
            h1 = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            ),
            h2 = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            ),
            h3 = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            ),
            h4 = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            ),
            h5 = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            h6 = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            ),
            body = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            ),
            small = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.W300,
            )
        )
    }
}
