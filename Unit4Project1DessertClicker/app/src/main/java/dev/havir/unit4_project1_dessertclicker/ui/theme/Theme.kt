package dev.havir.unit4_project1_dessertclicker.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = dark_primary,
    onPrimary = dark_onPrimary,
    secondaryContainer = dark_secondaryContainer,
    onSecondaryContainer = dark_onSecondaryContainer,
    background = dark_background,
)

private val LightColorScheme = lightColorScheme(
    primary = light_primary,
    onPrimary = light_onPrimary,
    secondaryContainer = light_secondaryContainer,
    onSecondaryContainer = light_onSecondaryContainer,
    background = light_background,
)

@Composable
fun Unit4Project1DessertClickerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // As our primary color (therefore color of the AppBar) is dark, we need to
    // tell to the system to use dark color on light theme for the icons on the
    // status bar.
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(
                window, view
            ).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
