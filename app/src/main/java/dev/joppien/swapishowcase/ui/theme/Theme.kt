package dev.joppien.swapishowcase.ui.theme

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

private val DarkColorScheme = darkColorScheme(
    primary = DarkSide_Primary,
    onPrimary = Color.White,
    secondary = DarkSide_Secondary,
    onSecondary = Color.Black,
    tertiary = DarkSide_Tertiary,
    onTertiary = DarkSide_OnTertiary,
    background = DarkSide_Background,
    onBackground = DarkSide_OnSurface,
    surface = DarkSide_Surface,
    onSurface = DarkSide_OnSurface,
    error = DarkSide_Error,
    onError = DarkSide_OnError
)

private val LightColorScheme = lightColorScheme(
    primary = LightSide_Primary,
    onPrimary = Color.White,
    secondary = LightSide_Secondary,
    onSecondary = Color.Black,
    tertiary = LightSide_Tertiary,
    onTertiary = Color.White,
    background = LightSide_Background,
    onBackground = LightSide_OnSurface,
    surface = LightSide_Surface,
    onSurface = LightSide_OnSurface,
    error = LightSide_Error,
    onError = Color.White
)

@Composable
fun SWAPIAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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
        content = content
    )
}