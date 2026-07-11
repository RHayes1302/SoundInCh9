package com.example.soundinch9.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = SoundInPrimaryDark,
    secondary = SoundInSecondaryDark,
    background = SoundInBackgroundDark,
    surface = SoundInSurfaceDark,
    onPrimary = SoundInOnPrimaryDark,
    onBackground = SoundInOnBackgroundDark,
    onSurface = SoundInOnSurfaceDark
)

private val LightColorScheme = lightColorScheme(
    primary = SoundInPrimary,
    secondary = SoundInSecondary,
    error = SoundInError,
    background = SoundInBackground,
    surface = SoundInSurface,
    onPrimary = SoundInOnPrimary,
    onBackground = SoundInOnBackground,
    onSurface = SoundInOnSurface

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun SoundInCh9Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes(
            extraSmall = RoundedCornerShape(size = 8.dp),
            small = RoundedCornerShape(size = 12.dp),
            medium = RoundedCornerShape(size = 16.dp),
            large = RoundedCornerShape(size = 24.dp),
            extraLarge = RoundedCornerShape(size = 32.dp)
        ),
        content = content
    )
}