package com.noasoftsolutions.machineCoding.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    background = backgroundColor,
    surface = primaryColor,
    onPrimary = lightGreenColor,
    onSecondary = lightGreenColor,
    onBackground = lightGreenColor,
    onSurface = Color.Black,
    tertiary = Color.Black,
    onTertiary = Color.Black,
    onSecondaryContainer = Color.Black,
    onSurfaceVariant = Color.Black,
    onPrimaryContainer = Color.Black,
    onError = Color.Black,
    error = Color.Black,
    onErrorContainer = Color.Black,
)

@Composable
fun NoaSoftSolutionsTheme(
    content: @Composable () -> Unit
) {

    val view = LocalView.current
    val window = (LocalContext.current as Activity).window
    val windowInsetsController =
        WindowCompat.getInsetsController(window, view)

    SideEffect {
        windowInsetsController.isAppearanceLightStatusBars = true
    }

    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

}