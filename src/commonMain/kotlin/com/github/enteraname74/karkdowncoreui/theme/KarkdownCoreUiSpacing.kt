package com.github.enteraname74.karkdowncoreui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max

data class KarkdownCoreUiSpacing(
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val veryLarge: Dp,
    val body: Dp,
    val maxHeader: Dp,
) {
    /**
     * Retrieve padding values for a text.
     */
    fun textPadding(
        headerLevel: Int
    ): Dp = if (headerLevel == 0) body else max((maxHeader.value - headerLevel).dp, body)

    companion object {
        val default = KarkdownCoreUiSpacing(
            small = 4.dp,
            medium = 8.dp,
            large = 16.dp,
            veryLarge = 24.dp,
            body = 3.dp,
            maxHeader = 20.dp
        )
    }
}
