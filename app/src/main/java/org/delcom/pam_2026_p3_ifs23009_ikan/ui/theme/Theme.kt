package org.delcom.pam_2026_p3_ifs23009_ikan.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = OceanPrimary,
    onPrimary = Color.White,
    primaryContainer = OceanPrimaryContainer,
    onPrimaryContainer = Color.White,

    secondary = OceanSecondary,
    onSecondary = Color.White,
    secondaryContainer = OceanSecondaryContainer,
    onSecondaryContainer = OceanPrimary,

    background = BackgroundLight,
    onBackground = OceanPrimary,

    surface = SurfaceLight,
    onSurface = OceanPrimary,

    error = ErrorRed,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = OceanPrimaryDark,
    onPrimary = Color.Black,

    background = BackgroundDark,
    onBackground = Color.White,

    surface = SurfaceDark,
    onSurface = Color.White,

    secondary = OceanSecondary,
    onSecondary = Color.Black,

    error = ErrorRed,
    onError = Color.White
)

@Composable
fun PAM2026Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}