package dev.joppien.swapishowcase.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkSide_Primary,
    onPrimary = DarkSide_OnPrimary,
    primaryContainer = DarkSide_PrimaryContainer,
    onPrimaryContainer = DarkSide_OnPrimaryContainer,
    secondary = DarkSide_Secondary,
    onSecondary = DarkSide_OnSecondary,
    secondaryContainer = DarkSide_SecondaryContainer,
    onSecondaryContainer = DarkSide_OnSecondaryContainer,
    tertiary = DarkSide_Tertiary,
    onTertiary = DarkSide_OnTertiary,
    tertiaryContainer = DarkSide_TertiaryContainer,
    onTertiaryContainer = DarkSide_OnTertiaryContainer,
    error = DarkSide_Error,
    onError = DarkSide_OnError,
    errorContainer = DarkSide_ErrorContainer,
    onErrorContainer = DarkSide_OnErrorContainer,
    background = DarkSide_Background,
    onBackground = DarkSide_OnBackground,
    surface = DarkSide_Surface,
    onSurface = DarkSide_OnSurface,
    surfaceVariant = DarkSide_SurfaceVariant,
    onSurfaceVariant = DarkSide_OnSurfaceVariant
)

private val LightColorScheme = lightColorScheme(
    primary = LightSide_Primary,
    onPrimary = LightSide_OnPrimary,
    primaryContainer = LightSide_PrimaryContainer,
    onPrimaryContainer = LightSide_OnPrimaryContainer,
    secondary = LightSide_Secondary,
    onSecondary = LightSide_OnSecondary,
    secondaryContainer = LightSide_SecondaryContainer,
    onSecondaryContainer = LightSide_OnSecondaryContainer,
    tertiary = LightSide_Tertiary,
    onTertiary = LightSide_OnTertiary,
    tertiaryContainer = LightSide_TertiaryContainer,
    onTertiaryContainer = LightSide_OnTertiaryContainer,
    error = LightSide_Error,
    onError = LightSide_OnError,
    errorContainer = LightSide_ErrorContainer,
    onErrorContainer = LightSide_OnErrorContainer,
    background = LightSide_Background,
    onBackground = LightSide_OnBackground,
    surface = LightSide_Surface,
    onSurface = LightSide_OnSurface,
    surfaceVariant = LightSide_SurfaceVariant,
    onSurfaceVariant = LightSide_OnSurfaceVariant
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

    CompositionLocalProvider(LocalSpacing provides AppSpacing()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}