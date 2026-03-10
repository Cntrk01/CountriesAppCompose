package com.mckstudio.countriesapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val DarkColorScheme = lightColorScheme(
    primary = CABlack,
    onPrimary = CAWhite,

    secondary = CAGray,
    tertiary = CAGrayLight,

    background = CABgWhite,
    surface = CACardWhite,

    outline = CAGrayLight,
    outlineVariant = CADividerLight,
    surfaceVariant = CAGrayLight
)

private val LightColorScheme = lightColorScheme(
    primary = CABlack,
    onPrimary = CAWhite,

    secondary = CAGray,
    tertiary = CAGrayLight,

    background = CABgWhite,
    surface = CACardWhite,

    outline = CAGrayLight,
    outlineVariant = CADividerLight,
    surfaceVariant = CAGrayLight
)

@Composable
fun CountriesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}