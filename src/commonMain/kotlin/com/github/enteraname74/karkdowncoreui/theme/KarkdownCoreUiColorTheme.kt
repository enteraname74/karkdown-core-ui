package com.github.enteraname74.karkdowncoreui.theme

import androidx.compose.ui.graphics.Color

data class KarkdownCoreUiColorTheme(
    val primary: ThemeComponent,
    val secondary: ThemeComponent,
    val secondaryContainer: ThemeComponent,
    val subText: Color,
    val accent: Color,
) {
    companion object {
        val lightDefault = KarkdownCoreUiColorTheme(
            primary = ThemeComponent(
                content = primaryColorDark,
                container = textColorDark,
            ),
            secondary = ThemeComponent(
                content = secondaryColorDark,
                container = textColorDark,
            ),
            secondaryContainer = ThemeComponent(
                content = secondaryContainerColor,
                container = textColorDark,
            ),
            subText = subTextColorDark,
            accent = accentColor,
        )
        val darkDefault = KarkdownCoreUiColorTheme(
            primary = ThemeComponent(
                content = primaryColorDark,
                container = textColorDark,
            ),
            secondary = ThemeComponent(
                content = secondaryColorDark,
                container = textColorDark,
            ),
            secondaryContainer = ThemeComponent(
                content = secondaryContainerColor,
                container = textColorDark,
            ),
            subText = subTextColorDark,
            accent = accentColor,
        )
    }
}

data class ThemeComponent(
    val content: Color,
    val container: Color,
)
