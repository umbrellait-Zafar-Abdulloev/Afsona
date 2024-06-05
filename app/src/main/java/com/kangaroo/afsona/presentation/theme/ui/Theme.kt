package com.kangaroo.afsona.presentation.theme.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kangaroo.afsona.presentation.theme.Shapes

private val DarkColorPalette = darkColors(
    primary = BlueBackground,
    primaryVariant = BlueBackground,
    secondary = Color.White
)

private val LightColorPalette = lightColors(
    primary = BlueBackground,
    primaryVariant = BlueBackground,
    secondary = Color.White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AfsonaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val controller = rememberSystemUiController()
    controller.setStatusBarColor(BlueBackground)

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}