package com.github.enteraname74.karkdowncoreui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class KarkdownCoreUiImageSize(
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val veryLarge: Dp,
    val huge: Dp,
) {
    companion object {
        val default = KarkdownCoreUiImageSize(
            small = 16.dp,
            medium = 32.dp,
            large = 64.dp,
            veryLarge = 128.dp,
            huge = 212.dp,
        )
    }
}
