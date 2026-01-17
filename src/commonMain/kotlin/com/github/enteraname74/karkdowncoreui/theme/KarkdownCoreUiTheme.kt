package com.github.enteraname74.karkdowncoreui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

val LocalKarkdownCoreUiColorTheme = compositionLocalOf { KarkdownCoreUiColorTheme.lightDefault }
val LocalKarkdownCoreUiSpacing = compositionLocalOf { KarkdownCoreUiSpacing.default }
val LocalKarkdownCoreUiTypography = compositionLocalOf { KarkdownCoreUiTypography.default }
val LocalKarkdownCoreUiImageSize = compositionLocalOf { KarkdownCoreUiImageSize.default }

object KarkdownCoreUiTheme {
    val colorScheme: KarkdownCoreUiColorTheme
        @Composable
        get() = LocalKarkdownCoreUiColorTheme.current

    val spacing: KarkdownCoreUiSpacing
        @Composable
        get() = LocalKarkdownCoreUiSpacing.current

    val typography: KarkdownCoreUiTypography
        @Composable
        get() = LocalKarkdownCoreUiTypography.current

    val imageSize: KarkdownCoreUiImageSize
        @Composable
        get() = LocalKarkdownCoreUiImageSize.current
}