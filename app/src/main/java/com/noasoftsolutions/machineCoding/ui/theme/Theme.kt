package com.noasoftsolutions.machineCoding.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    background = backgroundColor,
    surface = primaryColor,
    onPrimary = lightGreenColor,
    onSecondary = lightGreenColor,
    onBackground = lightGreenColor,
    onSurface = lightGreenColor,
    tertiary = Color.White,
    onTertiary = Color.White,
)

@Composable
fun NoaSoftSolutionsTheme(
    content: @Composable () -> Unit
) {

    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

}